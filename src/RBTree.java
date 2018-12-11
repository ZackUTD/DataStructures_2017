package pkg3345_project4;

/* Zack Oldham
 * zoo150030
 * 3/12/2017
 * Project 4
 * RBTreeNode
 * This class defines a simplified implementation of a Red-Black Tree
 */


public class RBTree 
{
    private RBTreeNode root = null;
    
    public RBTree(){}
    
    //inserts a new element into the tree
    public boolean insert(int value)
    {
        if(root == null)
        {
            root = new RBTreeNode(value);
            root.isRed = false;
            return true;
        }
        
        if(this.contains(value))
        {
            return false;
        }
        
        RBTreeNode current = root;
        RBTreeNode curParent = root;
        
        while(current != null && current.element != value)
        {
            if(value > current.element)
            {
                curParent = current;
                current = current.right;
            }
            else if(value < current.element)
            {
                curParent = current;
                current = current.left;
            }
            else
            {
                return false; //value is already in tree
            }
        }
        
        if(value > curParent.element)
        {
            curParent.right = new RBTreeNode(value);
            curParent.right.parent = curParent;
            curParent.right.isRed = true;
            status(curParent.right);
            return true;
        }
        
        curParent.left = new RBTreeNode(value);
        curParent.left.parent = curParent;
        curParent.left.isRed = true;
        status(curParent.left);
        return true;
    }
    
    //checks that a node is red (not null (null also being considered black) or black)
    private boolean nodeIsRed(RBTreeNode node)
    {
        if(node == null || !node.isRed)
        {
            return false;
        }
        
        return true;
    }
   
    //determines if any action needs to be taken to satisfy tree requirements and calls appropriate function
    private void status(RBTreeNode node) 
    {
        if(node != root) //if node is root it has no parent so we are good to go 
        {
            RBTreeNode gp = node.parent.parent;
        
            if(node.parent.isRed == true) //if parent.isRed is false then do nothing - we're all good
            {
                if(node.parent == gp.left)
                {      
                    if(!nodeIsRed(gp.right))
                    {   
                        if(node == node.parent.left)
                        {
                            llSwap(node);
                        }
                        else
                        {
                            lrSwap(node);
                        }   
                    }
                    else
                    {
                        reColor(node.element);
                        root.isRed = false;       
                    }
                }
                
                else
                {
                    if(!nodeIsRed(gp.left))
                    {
                        if(node == node.parent.right)
                        {
                            rrSwap(node);
                        }   
                        else
                        {
                            rlSwap(node);
                        }
                    }
                    else
                    {
                        reColor(node.element);
                        root.isRed = false;
                    }
                }      
            }
        }
    }
   
    //left-left swap on node
    private void llSwap(RBTreeNode node) 
    {
        RBTreeNode temp = node.parent.right;
        RBTreeNode gp = node.parent.parent;
        RBTreeNode ggp = gp.parent;
        
        node.parent.right = gp;
        node.parent.parent = ggp;
        node.parent.right.parent = node.parent;
        node.parent.right.left = temp;
        node.parent.isRed = false;
        node.parent.right.isRed = true;
        
        if(gp == root) //after swap make grandparent aware of new child
        {
            root = node.parent;
        }
        else if(ggp.left == gp)
        {
            ggp.left = node.parent;
        }
        else
        {
            ggp.right = node.parent;
        }
    }
    
    //right-right swap on node
    private void rrSwap(RBTreeNode node) 
    {
        RBTreeNode temp = node.parent.left;
        RBTreeNode gp = node.parent.parent;
        RBTreeNode ggp = gp.parent;
        
        node.parent.left = gp;
        node.parent.parent = ggp;
        node.parent.left.parent = node.parent;
        node.parent.left.right = temp;
        node.parent.isRed = false;
        node.parent.left.isRed = true;
        
        if(gp == root) //after swap make grandparent aware of new child 
        {
            root = node.parent;
        }
        else if(ggp.left == gp)
        {
            ggp.left = node.parent;
        }
        else
        {
            ggp.right = node.parent;
        }
    }
    
    //left-right swap on node
    private void lrSwap(RBTreeNode node) 
    {
        RBTreeNode temp1 = node.left;
        RBTreeNode temp2 = node.right;
        RBTreeNode gp = node.parent.parent;
        RBTreeNode ggp = gp.parent;
        
        node.left = node.parent;
        node.right = gp;
        node.parent = ggp;
        node.right.parent = node;
        node.left.parent = node;
        node.left.right = temp1;
        node.right.left = temp2;
        node.isRed = false;
        node.right.isRed = true;
        
        if(gp == root) //after swap make grandparent aware of new child
        {
            root = node;
        }
        else if(ggp.left == gp)
        {
            ggp.left = node;
        }
        else
        {
            ggp.right = node;
        }
    }
    
    //right-left swap on node
    private void rlSwap(RBTreeNode node) 
    {
        RBTreeNode temp1 = node.left;
        RBTreeNode temp2 = node.right;
        RBTreeNode gp = node.parent.parent;
        RBTreeNode ggp = gp.parent;
        
        node.left = gp;
        node.right = node.parent;
        node.parent = ggp;
        node.left.parent = node;
        node.right.parent = node;
        node.left.right = temp1;
        node.right.left = temp2;
        node.isRed = false;
        node.left.isRed = true;
        
        if(gp == root) //after swap make grandparent aware of new child
        {
            root = node;
        }
        else if(ggp.left == gp)
        {
            ggp.left = node;
        }
        else
        {
            ggp.right = node;
        }  
    }
    
    //re-colors the tree (determines which nodes, if any, must be re-colored)
    private void reColor(int dest) 
    {
        RBTreeNode current = root;
        
        while(current != null && current.element != dest)
        {
            if(nodeIsRed(current.left) && nodeIsRed(current.right))
            {
                swapColor(current);
                status(current);
            }
            if(dest > current.element)
            {
                current = current.right;
            }
            else 
            {
                current = current.left;
            }
        }    
    }
    
    //performs the change of colors
    private void swapColor(RBTreeNode node)
    {
        node.isRed = true;
        node.left.isRed = false;
        node.right.isRed = false;
        
        if(node == root)
        {
            node.isRed = false;
        }
        
    }
    
    //determines if an element is in the tree
    public boolean contains(int value) 
    {
        if(root == null)
        {
            return false;
        }
        
        RBTreeNode current = root;
        
        while(current != null)
        {
            if(value > current.element)
            {
                current = current.right;
            }
            else if(value < current.element)
            {
                current = current.left;
            }
            else
            {
                return true;
            }
        }
        
        return false;
    }
    
    //prints all nodes starting at node in-order (left, parent, child) (asterisk for red nodes)
    private void printTree(RBTreeNode node) 
    {
        if(node.left != null)
        {
            printTree(node.left);
        }
        
        if(node.isRed)
        {
            System.out.println("*" + node.element);
        }
        else
        {
            System.out.println(node.element);
        }
        
        if(node.right != null)
        {
            printTree(node.right);
        }
    }
    
    //calls printTree with root for user (user won't have access to root)
    public void printTree() 
    {
        if(root == null)
        {
            System.out.println("Tree is empty!");
        }
        else
        {
            printTree(root);
        }
    }
}

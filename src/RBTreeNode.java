package pkg3345_project4;

/* Zack Oldham
 * zoo150030
 * 3/12/2017
 * Project 4
 * RBTreeNode
 * This class defines the tree node for the Red-Black Tree
 */

public class RBTreeNode
{
    int element;
    RBTreeNode left;
    RBTreeNode right;
    RBTreeNode parent;
    boolean isRed;
    
    RBTreeNode(){}
    
    RBTreeNode(int e)
    {
        element = e;
    }
}

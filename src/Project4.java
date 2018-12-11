package pkg3345_project4;

/* Zack Oldham
 * zoo150030
 * 3/12/2017
 * Project 4
 * This main class implements an instance of a simplified Red-Black Tree and provides a user interface
 */

import java.util.Scanner;
import java.lang.NumberFormatException;


public class Project4 
{
    public static void main(String[] args) 
    {
        RBTree tree = new RBTree();
        Scanner input = new Scanner(System.in);
        String inStr;
        int choice;
        int num;
        
        System.out.println("1: Insert");
        System.out.println("2: Contains");
        System.out.println("3: Print Tree");
        System.out.print("Enter your choice or zero to quit:  ");
        inStr = input.nextLine();
        
        
        while((choice = check(inStr)) < 0)
        {
            System.out.println("You must enter a number between 1 and 3");
            System.out.println("Try again: ");
            
            System.out.println("1: Insert");
            System.out.println("2: Contains");
            System.out.println("3: Print Tree");
            System.out.print("Enter your choice or zero to quit:  ");
            inStr = input.nextLine();
        }
        
        while(choice > 0)
        {
            switch(choice)
            {
                case 1: 
                    System.out.println("Your choice: " + choice);
                    System.out.print("Enter the number you wish to add: ");
                    inStr = input.nextLine();
                    while((num = check(inStr)) < 0)
                    {
                        System.out.print("You must enter an integer, try again: ");
                        inStr = input.nextLine();
                    }
                    
                    tree.insert(num);
                    break;
                    
                case 2: 
                    System.out.println("Your choice: " + choice);
                    System.out.print("Enter the number you wish to check for: ");
                    inStr = input.nextLine();
                    while((num = check(inStr)) < 0)
                    {
                        System.out.print("You must enter an integer, try again: ");
                        inStr = input.nextLine();
                    }
                    
                    if(tree.contains(num))
                    {
                        System.out.println("That number is in the tree!");
                    }
                    else
                    {
                        System.out.println("That number is not in the tree.");
                    }
                    
                    break;
                
                case 3: 
                    tree.printTree();
                    break;
                    
                default: 
                    System.out.println("You must enter a number between 1 and 3 or 0 to quit.");
                    System.out.print("Try again: ");
            }
            
            System.out.println("1: Insert");
            System.out.println("2: Contains");
            System.out.println("3: Print Tree");
            System.out.print("Enter your choice or zero to quit:  ");
            inStr = input.nextLine();
            
            while((choice = check(inStr)) < 0)
            {
                System.out.println("You must enter a number between 1 and 3");
                System.out.println("Try again: ");
            
                System.out.println("1: Insert");
                System.out.println("2: Contains");
                System.out.println("3: Print Tree");
                System.out.println("Enter your choice or zero to quit:  ");
                inStr = input.nextLine();
            }   
        }
    }
    
    //checks that user input is numeric
    public static int check(String str) 
    {
        try
        {
            int val = Integer.parseInt(str);
            return val;
        }
        catch(NumberFormatException ex)
        {
            return -1;
        }
    }
}

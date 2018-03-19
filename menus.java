/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcproject;

import java.util.Scanner;
import java.sql.*;
/**
 *
 * @author kobe
 */
public class menus {
    Scanner reader = new Scanner(System.in);
    Statement st;
    
    public menus(Statement st){
        this.st = st;
    }
    
    void mainMenu(){
        System.out.println("Select which table you want to view/edit");
        
        
        boolean loopAgain = true;
        while(loopAgain){
            System.out.println("1: Books, 2: Publishers, 3: Writing Groups, 4: Exit");
            int n = reader.nextInt();
            switch(n){
                case 1: 
                    loopAgain = booksMenu();
                    break;                
                case 2: 
                    loopAgain = publishersMenu();
                    break;                
                case 3: 
                    loopAgain = writingGroupsMenu();
                    break;                
                case 4: 
                    loopAgain = false;
                    break;                
                default: 
                    System.out.println("That was not a valid option... please try again");
                    break;                
            }
        }
    }

    private boolean booksMenu() {
        System.out.println("Select which operation you would like to perform on the book table");
        boolean loopAgain = true;
        while(loopAgain){
            System.out.println("1: List all book titles, 2: List all the data for a specific book, 3: Insert a new book, 4: Remove a book, 5: Go back, 6: Exit");
            int n = reader.nextInt();
            switch(n){
                case 1: 
                    System.out.println("Get all book titles");
                    break;
                case 2: 
                    System.out.println("List all the data for a specific book"); //including publisher and writing group
                    break;
                case 3: 
                    System.out.println("Insert a new book");
                    break;
                case 4: 
                    System.out.println("Remove a book");
                    break;
                case 5: 
                    return true;
                case 6: return false;
                default: 
                    System.out.println("That was not a valid option... please try again");
                    break;
            }
        }
        return false;
    }

    private boolean publishersMenu() {
        System.out.println("Select which operation you would like to perform on the Publishers table");
        
        boolean loopAgain = true;
        while(loopAgain){
            System.out.println("1: List all publishers, 2: List all the data for a specific publisher, 3: Insert a new publisher, 4: Update publisher, 5: Go back, 6: Exit");
            int n = reader.nextInt();
            switch(n){
                case 1: System.out.println("Get all publishers");
                    break;
                case 2: System.out.println("List all the data for a specific publisher");
                    break;
                case 3: System.out.println("Insert a new publisher");
                    break;
                case 4: System.out.println("Update publisher");
                    break;
                case 5: return true;
                case 6: return false;
                default: System.out.println("That was not a valid option... please try again");
                    break;
            }
        }
        return false;
    }

    private boolean writingGroupsMenu() {
        System.out.println("Select which operation you would like to perform on the Writing Groups table");        
        boolean loopAgain = true;
        while(loopAgain){           
            System.out.println("1: List all writing groups, 2: List all the data for a specific writing group, 3: Go back, 4: Exit");
            int n = reader.nextInt();
            switch(n){
                case 1: System.out.println("Get all writing group names");
                    break;
                case 2: System.out.println("List all the data for a specific writing group");
                    break;
                case 3: return true;
                case 4: return false;
                default: System.out.println("That was not a valid option... please try again");
                    break;
            }
        }
        return false;
    }
    
}

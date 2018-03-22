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
    databaseFunctions dbFuncs;
    
    /*
        menus class gets an instance of a databaseFunctions object
    */
    public menus(Statement st){
        this.dbFuncs = new databaseFunctions(st);
    }
    
    /*
        Main menu. User can select which table to operate on.
    */
    void mainMenu() throws SQLException{
        System.out.println("Select which table you want to view/edit");
        int n;
        boolean loopAgain = true;
        while(loopAgain){
            System.out.println("1: Books, 2: Publishers, 3: Writing Groups, 4: Exit");
            try{
                n = Integer.parseInt(reader.nextLine());
            }
            catch(Exception e){
                n = 5;
            }
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
    
    /**
     * Menu for the getting specific publisher from the database
     */
    private boolean specificPublisherMenu() throws SQLException{
        boolean isValid = false;
        String publisherName, sqlStr;
        while(!isValid){
            sqlStr = "Select * from Publishers Where publisherName = ";
            System.out.println("Please provide a publisher name or enter '<' to go back:");
            publisherName = reader.nextLine();
            if(publisherName.equals("<"))return true;
            sqlStr = sqlStr + "'" + publisherName + "'";
            if(dbFuncs.isThereRecord(sqlStr)){
                dbFuncs.getAllDataForPublisher(publisherName);
            }
        }
        return false;
    }
    
    /**
     * Menu for the getting specific writing group from the database
     */
    private boolean specificWritingGroupMenu() throws SQLException{
        boolean isValid = false;
        String groupName;
        while(!isValid){
            String sqlStr = "Select * from WritingGroups Where groupName = ";
            System.out.println("Please provide a group name or enter '<' to go back:");
            groupName = reader.nextLine();
            if(groupName.equals("<"))return true;
            sqlStr = sqlStr + "'" + groupName + "'";
            if(dbFuncs.isThereRecord(sqlStr)){
                dbFuncs.getAllDataForWritingGroup(groupName);
            }
        }
        return false;
    }
    
     /**
     * Menu for the getting a specific book from the database
     */
    private boolean specificBookMenu() throws SQLException{
        boolean isValid = false;

        String bookTitle;
        String publisherName;
        while(!isValid){
            String sqlStr = "Select * from Books Where bookTitle = ";
            System.out.println("Please provide a book title or enter '<' to go back:");
            bookTitle = reader.nextLine();
            if(bookTitle.equals("<"))return true;
            sqlStr = sqlStr + "'" + bookTitle + "'";
            if(dbFuncs.isThereRecord(sqlStr)){
                System.out.println("Please provide a publisher name or enter '<' to go back:");
                publisherName = reader.nextLine();
                if(publisherName.equals("<"))return true;
                sqlStr = sqlStr + " AND publisherName = " + "'" + publisherName + "'";
                if(dbFuncs.isThereRecord(sqlStr)){
                    isValid = true;
                    System.out.println("Found the record");
                    dbFuncs.getAllDataForBook(bookTitle, publisherName);
                }
                else{
                    System.out.println("No book exists with the given publisher name for the given book title");
                }
            }
            else{
                System.out.println("No book exists with the given book title");
            }
        }
        return true;
    }

    /**
     * Menu for the books
     */
    private boolean booksMenu() throws SQLException {
        System.out.println("Select which operation you would like to perform on the book table");
        boolean loopAgain = true;
        int n;
        while(loopAgain){
            System.out.println("1: List all book titles, 2: List all the data for a specific book, 3: Insert a new book, 4: Remove a book, 5: Go back, 6: Exit");
            try{
                n = Integer.parseInt(reader.nextLine());
            }
            catch(Exception e){
                n = 7;
            }
            switch(n){
                case 1: 
                    System.out.println("Get all book titles");
                    dbFuncs.getAllBookTitles();
                    break;
                case 2: 
                    System.out.println("List all the data for a specific book"); //including publisher and writing group
                    loopAgain = specificBookMenu();
                    break;
                case 3: 
                    System.out.println("Insert a new book");
                    loopAgain = insertNewBookMenu();
                    break;
                case 4: 
                    System.out.println("Remove a book");
                    loopAgain = removeABookMenu();
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
    
    /**
     * Menu for removing a book from the database
     */
    private boolean removeABookMenu() throws SQLException{
        boolean isValid = false;
        String bookTitle, publisherName, strSql;
        while(!isValid){
            System.out.println("Title of book to remove or '<' to go back");
            bookTitle = reader.nextLine();
            if(bookTitle.equals('<'))return true;
            System.out.println("Name of publisher of book to remove or '<' to go back");
            publisherName = reader.nextLine();
            if(publisherName.equals('<'))return true;
            strSql = "Select * From Books Where bookTitle = '" + bookTitle + "' AND publisherName = '" + publisherName + "'";
            if(!dbFuncs.isThereRecord(strSql)){
                System.out.println("That book doesn't exist! Try again...");
            }
            else{
                dbFuncs.deleteBook(bookTitle, publisherName);
                return true;
            }
            
        }
        return false;
    }
    
    /**
     * Menu for inserting a new book into the database
     */
    private boolean insertNewBookMenu() throws SQLException{
        String selectSql, selectSql2;
        boolean isValid = false;
        String attributeNames[] = {"title", "group", "publisher", "year", "number of pages"};
        String stringAttributes[] = new String[3];//title, group, publisher;
        int intAttributes[] = new int[2]; //year, numPages;
        
        while(!isValid){
            for(int i = 0; i < 3; i++){
                System.out.println("Please enter the " + attributeNames[i]);
                if(i!=0)System.out.println("Type '...' if you want to see the list of " + attributeNames[i] + "s or '<' to go back");
                stringAttributes[i] = reader.nextLine();
                if(i!=0){
                    if(stringAttributes[i].equals("<"))return true;
                    else if(stringAttributes[i].equals("...")){
                        selectSql = "Select ";
                        if(i==1) selectSql = selectSql + "groupName From WritingGroups";
                        else selectSql = selectSql + "publisherName From Publishers";
                        dbFuncs.getAllWhatever(selectSql);
                        i--;
                    }
                    else if(stringAttributes[i].length() > 50){
                        System.out.println("Too long of an input. Must be less than 50 characters.");
                        i--;
                    }
                    else{
                        if(i==1)selectSql = "Select * From WritingGroups Where groupName = '" + stringAttributes[i] + "'"; 
                        else selectSql = "Select * From Publishers Where publisherName = '" + stringAttributes[i] + "'"; 
                        if(!dbFuncs.isThereRecord(selectSql)){
                            System.out.println("There is no record with " + attributeNames[i] + " set to " + stringAttributes[i]);
                            i--;
                        }
                    }
                }
            }  
            selectSql = "Select * From Books Where bookTitle = '" + attributeNames[0] + "' AND groupName = '" + attributeNames[1] + "'";
            selectSql2 = "Select * From Books Where bookTitle = '" + attributeNames[0] + "' AND publisherName = '" + attributeNames[2] + "'";
            String input;
            if(!(dbFuncs.isThereRecord(selectSql) || dbFuncs.isThereRecord(selectSql2))){
                for(int i = 0; i < 2; i++){
                    System.out.println("Please enter the " + attributeNames[i+3] + " or '<' to go back");
                    input = reader.nextLine();
                    if(input.equals('<'))return true;
                    try{
                        intAttributes[i] = Integer.parseInt(input);
                    }
                    catch(Exception e){
                        System.out.println(e);
                        i--;
                    }
                }
                dbFuncs.insertNewBook(stringAttributes, intAttributes);
                return true;
            }
            else{
                System.out.println("You entered a duplicate book...");
            }
        }
        return false;
    }
    
    /**
     * Menu for creating a new publisher and updating all books of and old publisher with the new publisher
     */
    private boolean newPubAndUpdateMenu() throws SQLException {
        boolean isValid = false;
        String[] nominators = {"name","address","phone", "email"};
        String[] publisher = new String[4];
        //String pubName, pubAddress, pubPhone, pubEmail;
        System.out.println("You will now provide us with the publisher details. At any time you may enter '<' to go back to the previous menu.");
        for(int i = 0; i < 4; i++){
            System.out.println("Please enter the " + nominators[i]);
            publisher[i] = reader.nextLine();
            if(publisher[i].equals("<"))return true;
            if(i==0 && dbFuncs.isThereRecord("Select * From Publishers Where publisherName = '" + publisher[i] + "'")){
                System.out.println("There already exists a publisher with that name...");
                i--;
            }
            else if(publisher[i].length() > 50){
                System.out.println("That is too long of an input. Must be less than 50 characters");
                i--;
            }
        }
        System.out.println("You are about to insert a new pubisher with");
        for(int i = 0; i < 4; i++){
            System.out.println(nominators[i] + ": " + publisher[i]);
        }
        System.out.println("Is this correct [Y/N]?");
        while(!isValid){
            String response = reader.nextLine().toUpperCase();
            switch (response) {
                case "Y":
                    isValid = true;
                    dbFuncs.insertNewPublisher(publisher);
                    return updatePublisherMenu(publisher[0]);
                case "N":
                    System.out.println("Going back...");
                    return true;
                default:
                    System.out.println("That was not a valid response. Try again [Y/N]...");
                    break;
            }
        }
        return false;
    }

    /**
     * Menu for updating the publisher. It is only called from the newPubAndUpdateMenu
     */
    private boolean updatePublisherMenu(String pubName) throws SQLException{
        boolean isValid = false;
        String oldPublisher;
        System.out.println("Do you want to update all books of a certain publisher with this publisher? [Y/N]");
        while(!isValid){
            String response = reader.nextLine().toUpperCase();
            switch (response) {
                case "Y":
                    while(!isValid){
                        System.out.println("What is the name of the old publisher you want to replace? You can enter '<' to go back.");
                        oldPublisher = reader.nextLine();
                        if(oldPublisher.equals("<"))return true;
                        if(oldPublisher.equals(pubName)){
                            System.out.println("You entered the same name");
                        }
                        else if(!dbFuncs.isThereRecord("Select * From Publishers Where publisherName = '" + oldPublisher + "'")){
                            System.out.println("There is no publisher with that name");
                        }
                        else{
                            dbFuncs.updateBooksWithNewPub(oldPublisher, pubName);    
                            return true;
                        }
                    }                  
                    return true;
                case "N":
                    System.out.println("Going back...");
                    return true;
                default:
                    System.out.println("That was not a valid response. Try again [Y/N]...");
                    break;
            }
        }    
        return false;
    }
    
    /**
     * Menu for the publishers
     */
    private boolean publishersMenu() throws SQLException {
        System.out.println("Select which operation you would like to perform on the Publishers table");
        int n;
        boolean loopAgain = true;
        ResultSet rs;
        while(loopAgain){
            System.out.println("1: List all publishers, 2: List all the data for a specific publisher, 3: "
                    + "Insert new publisher and use it to update all published books by another publisher, 4: Go back, 5: Exit");
            try{
                n = Integer.parseInt(reader.nextLine());
            }
            catch(Exception e){
                n = 6;
            }
            switch(n){
                case 1: System.out.println("Get all publishers");
                    dbFuncs.getAllPublishers();
                    break;
                case 2: System.out.println("List all the data for a specific publisher");
                    loopAgain = specificPublisherMenu();
                    break;
                case 3: System.out.println("Insert a new publisher");
                    loopAgain = newPubAndUpdateMenu();
                    break;
                case 4: return true;
                case 5: return false;
                default: System.out.println("That was not a valid option... please try again");
                    break;
            }
        }
        return false;
    }

    /**
     * Menu for the writing groups
     */
    private boolean writingGroupsMenu() throws SQLException {
        System.out.println("Select which operation you would like to perform on the Writing Groups table");        
        boolean loopAgain = true;
        ResultSet rs;
        int n;
        while(loopAgain){           
            System.out.println("1: List all writing groups, 2: List all the data for a specific writing group, 3: Go back, 4: Exit");
            try{
                n = Integer.parseInt(reader.nextLine());
            }
            catch(Exception e){
                n = 5;
            }
            switch(n){
                case 1: System.out.println("Get all writing group names");
                    dbFuncs.getAllWritingGroups();
                    break;
                case 2: 
                    System.out.println("List all the data for a specific writing group");
                    loopAgain = specificWritingGroupMenu();
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

package jdbcproject;

import java.sql.*;
import java.util.Scanner;

public class JDBCProject {
    
    public static void main(String[] args) {
        //Attempt to connect to the database 
        try(Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/mOpkinsProj", "Luca", "pass");){  
            Class.forName("org.apache.derby.jdbc.ClientDriver"); //get the database class type
            con.setAutoCommit(true); 
            //Create a statement with the connection. Only use one instance throughout the whole program. It is passed to the menus object
            //Where it is saved as a private member
            Statement st = con.createStatement(); 

            //Members object with statement provided
            menus menus = new menus(st);
            menus.mainMenu();

            con.close(); //close the connection
        }//If there was an error
        catch(SQLException | ClassNotFoundException e){
            System.out.println(e);
        }
    }

}

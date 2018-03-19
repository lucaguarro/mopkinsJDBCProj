package jdbcproject;

import java.sql.*;
import java.util.Scanner;

public class JDBCProject {
    
    public static void main(String[] args) {
        //Luca, pass
        try(Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/mOpkinsProj", "Luca", "pass");){
            //databaseFunctions dbFuncs = new databaseFunctions(con);  
            Class.forName("org.apache.derby.jdbc.ClientDriver"); //2nd step
            Statement st = con.createStatement();
            /*
                You will have to change the username and pass
                String sqlStr = "Select * from books";
                ResultSet rs = st.executeQuery(sqlStr);
                rs.next();
                String sname = rs.getString(2);
                System.out.println(sname);
            */
            menus menus = new menus(st);
            menus.mainMenu();

            con.close();
        }
        catch(SQLException | ClassNotFoundException e){
            System.out.println(e);
        }
    }
    /*menuClass
    String promptMessage
    String[] options
    Menu parentMenu;
    Menu[] childrenMenus
    
    
    */

}

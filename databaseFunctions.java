package jdbcproject;
import java.sql.*;
/**
 *
 * @author kobe
 */
public class databaseFunctions {
    private Statement st;
    public databaseFunctions(Statement st){
        this.st = st;
    }
    
    /*
        Gets and prints all the records in the sql statement passed into it
    */
    void getAllWhatever(String sqlStr){
        try(ResultSet rs = st.executeQuery(sqlStr);){
            printRS(rs);
        }
        catch(SQLException e){
            System.out.println(e);
        }  
    }

    /*
        Gets and prints all the writing groups
    */    
    void getAllWritingGroups(){
        String sqlStr = "Select * from WritingGroups";
        try(ResultSet rs = st.executeQuery(sqlStr);){
            printRS(rs);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    /*
        Gets and prints all the book titles
    */
    void getAllBookTitles(){
        String sqlStr = "Select bookTitle from Books";
        try(ResultSet rs = st.executeQuery(sqlStr);){
            printRS(rs);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    
    /*
        Gets and prints all the publishers
    */
    void getAllPublishers(){
        String sqlStr = "Select * from Publishers";
        try(ResultSet rs = st.executeQuery(sqlStr);){
            printRS(rs);
            rs.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    
    /*
        Returns true if the sql statement passed into contains any rows. False if otherwise.
    */    
    boolean isThereRecord(String sqlStr) throws SQLException{
        ResultSet rs = st.executeQuery(sqlStr);
        boolean isThere = rs.next();
        rs.close();
        return isThere;
    }
    
    /*
       Prints all content in the recordset passed into it
    */
    void printRS(ResultSet rs) throws SQLException{
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        String toPrint;
        while(rs.next()){
            for(int i = 1; i <= columnsNumber; i++){
                String columnValue = rs.getString(i);
                toPrint = rsmd.getColumnName(i) + ": " + columnValue;
                if (i != columnsNumber){toPrint = toPrint + ",";}
                System.out.println(toPrint);
                
            } 
            System.out.println();
        }
    }  
    
    /*
        Does a natural join with the 3 tables to get and print all of a book's content
    */
    void getAllDataForBook(String bookTitle, String publisherName) throws SQLException{
        String sqlStr = "Select * From Books NATURAL JOIN Publishers NATURAL JOIN WritingGroups WHERE bookTitle = '" + bookTitle +
                "' AND publisherName = '" + publisherName + "'";
        ResultSet rs = st.executeQuery(sqlStr);
        printRS(rs);
    }
    
    /*
        Does a natural join with the 3 tables to get and print all of a writing groups's content
    */
    void getAllDataForWritingGroup(String groupName) throws SQLException{
        String sqlStr = "Select * From WritingGroups NATURAL JOIN Books NATURAL JOIN Publishers WHERE groupName = '" + groupName + "'";
        ResultSet rs = st.executeQuery(sqlStr);
        printRS(rs);
    }
    
    /*
        Does a natural join with the 3 tables to get and print all of a publisher's content
    */
    void getAllDataForPublisher(String publisherName) throws SQLException{
        String sqlStr = "Select * From Publishers NATURAL JOIN Books NATURAL JOIN WritingGroups WHERE publisherName = '" + publisherName + "'";
        ResultSet rs = st.executeQuery(sqlStr);
        printRS(rs);      
    }
    
    /*
        Inserts a new publisher into the database. The publisher array contains all of the fields necessary.
    */
    void insertNewPublisher(String[] publisher) throws SQLException{
       String sqlStr = "INSERT INTO Publishers (publisherName, publisherAddress, publisherPhone, publisherEmail)"
                                + "VALUES ('" + publisher[0] + "', '" + publisher[1] + "', '" + publisher[2] + "', '" + publisher[3] + "')";
       st.executeUpdate(sqlStr);
       System.out.println("New publisher added.");
    }
    
    /*
        Inserts a new book into the database. stringAttrs hold the values for the char[50] type attributes and intAttrs hold the values
        for the integer type attributes
    */
    void insertNewBook(String[] stringAttrs, int[] intAttrs) throws SQLException{//title, group, publisher;year, pages;
        String sql = "INSERT INTO Books (BookTitle, PublisherName, yearPublished, GroupName,numberPages)"
                         + "VALUES ('" + stringAttrs[0] + "', '" + stringAttrs[2] + "'," + intAttrs[0] + ", '" + stringAttrs[1] + "', "+ intAttrs[1] +") ";
        st.executeUpdate(sql);
        System.out.println("New book has been inserted");
    }
    
    /*
        Deletes a book from the database with the booktitle and publishername given
    */
    void deleteBook(String bookTitle, String publisherName) throws SQLException{
        String sql = "DELETE FROM Books WHERE bookTitle = '" + bookTitle + "' AND publisherName = '" + publisherName + "'";
        st.executeUpdate(sql);
        System.out.println("Book deleted");
    }
    
    /*
        Updates all the books with publisher where publisherName of oldPublisher to have publisher where publisherName is newPublisher
    */
    void updateBooksWithNewPub(String oldPublisher, String newPublisher) throws SQLException{
       String booksSQL = "UPDATE Books " + "SET publisherName = '" + newPublisher + "' WHERE publisherName = '" + oldPublisher + "'";
       st.executeUpdate(booksSQL);  
       System.out.println("updated");
    }
}
//String, string, int, string, int
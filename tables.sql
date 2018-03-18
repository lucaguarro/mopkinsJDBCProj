--/*Table structure for WritingGroups*/

/*Drop Table Books;
Drop Table WritingGroups;
Drop Table Publishers;*/

/*
git remote add origin https://github.com/lucaguarro/mopkinsJDBCProj.git
git push -u origin master

*/

CREATE TABLE WritingGroups (
    groupName varchar(50) NOT NULL,
    headWriter varchar(50) NOT NULL,
    yearFormed int NOT NULL,
    topic varchar(50) NOT NULL,
    CONSTRAINT writingGroupPK PRIMARY KEY (groupName)
) ;

--/*Table structure for Publishers*/

CREATE TABLE Publishers (
    publisherName varchar(50) NOT NULL,
    publisherAddress varchar(50) NOT NULL,
    publisherPhone varchar(50) NOT NULL,
    publisherEmail varchar(50) NOT NULL,
    CONSTRAINT pubPK PRIMARY KEY (publisherName)
);

--/*Table structure for books*/

CREATE TABLE Books (
    bookTitle varchar(50) NOT NULL,
    publisherName varchar(50) NOT NULL,
    yearPublished varchar(50) NOT NULL,
    groupName varchar(50) NOT NULL,
    numberPages varchar(50) DEFAULT NULL,
    CONSTRAINT booksGroupNameFK FOREIGN KEY (groupName) REFERENCES WritingGroups (groupName),
    CONSTRAINT booksPublisherNameFK FOREIGN KEY (publisherName) REFERENCES Publishers (publisherName),
    CONSTRAINT booksPK PRIMARY KEY (groupName, bookTitle)
    
    INSERT INTO WritingGroups
    VALUES('Happy Bookers', 'Alicia Smith', 2010, 'Fiction'),
          ('Readers Dozen', 'Bob Anderson', 2008, 'Horror'),
          ('Summer Book Club', 'Mike Jones', 2016, 'History'),
          ('Rabid Readers', 'Alex Thomas', 2013, 'Fantasy'),
          ('Witty Women Book Club', 'Nicole Johnson', 2011, 'Romance');
        
    INSERT INTO Publishers
    VALUES('Secker & Warburg', '123 A. Lane', '310-335-3928', 's&w@gmail.com'),
          ('Archibald Constable and Company', '456 B. Street', '310-234-9720', 'acc@gmail.com'),
          ('W.W Norton', '789 C. Drive', '562-356-4459', 'wwn@hotmail.com'),
          ('Allen & Unwin', '1011 D. Way', '323-453-0126', 'a&u@aol.com'),
          ('Harper & Row', '1012 E. Street', '767-540-1233', 'hr@yahoo.com');

    INSERT INTO Books
    VALUES('Happy Bookers', '1984', 'Secker & Warburg', '1949', 327),
          ('Readers Dozen', 'Dracula', 'Archibald Constable and Company', '1897', 745),
          ('Summer Book Club', 'Guns Germs and Steel', 'W.W Norton', '1997', 510),
          ('Rabid Readers', 'The Lord of the Rings', 'Allen & Unwin', '1954', 924),
          ('Witty Women Book Club', 'Love Story', 'Harper & Row', '1970', 148);

select  * from WritingGroups
NATURAL JOIN Publishers
NATURAL JOIN Books;
);

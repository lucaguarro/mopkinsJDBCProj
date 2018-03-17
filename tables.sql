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
);

--/*Table structure for WritingGroups*/

Drop Table Books;
Drop Table WritingGroups;
Drop Table Publishers;

CREATE TABLE WritingGroups (
    groupName varchar(50) NOT NULL,
    headWriter varchar(50) NOT NULL,
    yearFormed int NOT NULL,
    subject varchar(50) NOT NULL,
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
    yearPublished int NOT NULL,
    groupName varchar(50) NOT NULL,
    numberPages int DEFAULT NULL,
    CONSTRAINT booksGroupNameFK FOREIGN KEY (groupName) REFERENCES WritingGroups (groupName),
    CONSTRAINT booksPublisherNameFK FOREIGN KEY (publisherName) REFERENCES Publishers (publisherName),
    CONSTRAINT booksPK PRIMARY KEY (groupName, bookTitle)
);

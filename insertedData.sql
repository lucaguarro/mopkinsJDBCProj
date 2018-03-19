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

--bookTitle, publisherName, yearPublished, groupName, numberPages
INSERT INTO Books
VALUES('1984', 'Secker & Warburg', 1949, 'Happy Bookers', 327),
        ('Dracula', 'Archibald Constable and Company', 1897, 'Readers Dozen', 745),
        ('Guns Germs and Steel', 'W.W Norton', 1997, 'Summer Book Club', 510),
        ('The Lord of the Rings', 'Allen & Unwin', 1954, 'Rabid Readers', 924),
        ('Love Story', 'Harper & Row', 1970, 'Witty Women Book Club', 148);

select  * from WritingGroups
NATURAL JOIN Publishers
NATURAL JOIN Books;
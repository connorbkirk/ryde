#
#   The following is a MySQL script to populate the database for the Ryde system
#   Author: John Porter
#

#Add one owner to the database
INSERT INTO users
VALUES('1','John','Porter','jayp','password');

#Add cars to the database
INSERT INTO cars
VALUES('1','sedan','Toyota','Camry','2016','silver','1','200','nice and reliable car');

INSERT INTO cars
VALUES('2','sedan','Saturn','Ion','2004','silver', '1','75','Car has been in two accidents, completely fixed up and safe');

INSERT INTO cars
VALUES('3','truck','Ford','BigTruck','2024','chrome','2','200','You have not driven a car so impractical before');

INSERT INTO cars
VALUES('4','sedan','Mercedes','Mersais','2016','blue','5', '1','He will roll up in the Mersais with a check to your work place' );

INSERT INTO cars
VALUES('5','boat', 'Toyota', 'BigRaft', '2004','white','4','20','Before you say there is no such thing just trust us that it exists');

INSERT INTO cars
VALUES('6','truck','Ford','F650','2018','black','3','100','Such a big truck it could move a house');

#add rental dates for the cars
INSERT INTO rental_dates
VALUES('1','1','2016-9-16','2016-11-15');

INSERT INTO rental_dates
VALUES('2','2','2016-9-22', '2016-9-23');

#Add users to the database
INSERT INTO users(id, firstname,lastname,username,pswrd)
VALUES('2','Connor', 'Kirk','username','bologna1');

INSERT INTO users
VALUES('3', 'Mehdi', 'Allahyari', 'mehdi', 'firebug');

INSERT INTO users
VALUES('4','Sahi', 'Nimmakayalu', 'sahi', 'password');

INSERT INTO users
VALUES('5','Kanye','West','kanye','kimk');

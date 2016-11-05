#
#   The following is a MySQL script to populate the database for the Ryde system
#   Author: John Porter
#

#Add one owner to the database
INSERT INTO users 
VALUES('1','John','Porter','jayp','password');

#Add two cars to the database
INSERT INTO cars 
VALUES('1','sedan','Toyota','Camry','2016','silver','1','200','nice and reliable car');

INSERT INTO cars 
VALUES('2','sedan','Saturn','Ion','2004','silver', '1','75','Car has been in two accidents, completely fixed up and safe');

#add rental dates for the Toyota
INSERT INTO rental_dates
VALUES('1','1','2016-9-16','2016-11-15');

#Add two users to the database
INSERT INTO users(id, firstname,lastname,username,pswrd)
VALUES('2','Connor', 'Kirk','username','bologna1');

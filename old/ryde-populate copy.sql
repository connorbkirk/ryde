#
#   The following is a MySQL script to populate the database for the Ryde system
#   Author: John Porter
#

#Add one owner to the database
INSERT INTO owners 
VALUES('1','John','Porter','jay@ryde.com');

#Add two cars to the database
INSERT INTO cars 
VALUES('1','Toyota','Camry','2016','silver','1','200','nice and reliable car');

INSERT INTO cars 
VALUES('2','Saturn','Ion','2004','silver', '1','75','Car has been in two accidents, completely fixed up and safe');

#Add two users to the database
INSERT INTO users(id, firstname,lastname,username,pswrd)
VALUES('1','Connor', 'Kirk','username','bologna1');

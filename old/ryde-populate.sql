#
#   The following is a MySQL script to populate the database for the Ryde system
#   Author: John Porter
#

#Add two cars to the database
INSERT INTO cars (id, make, model, carYear, color, ownerID)
VALUES('1','Toyota','Camry','2016','silver','1');

INSERT INTO cars (id, make, model, carYear, color, ownerID)
VALUES('2','Saturn','Ion','2004','silver', '1');

#Add one owner to the database
INSERT INTO owners (id, firstname,lastname,email)
VALUES('1','John','Porter','jay@ryde.com');

#Add two users to the database
INSERT INTO users(id, firstname,lastname,username,pswrd)
VALUES('1','Connor', 'Kirk','username','bologna1');

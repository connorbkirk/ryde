#
#   The following is a MySQL script to populate the database for the Ryde system
#   Author: John Porter
#

#Add one owner to the database
INSERT INTO users
VALUES('1','John','Porter','jayp','66bb2040b1142c33537b6d91723d4eb6','7054444444','jayp@ryde.com');

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

INSERT INTO cars
VALUES('7','suv','Volkswagon','Thing','1972','yellow','2','60','Comes with the parts to turn into boat');

INSERT INTO cars
VALUES('8','suv','Kia','Sorento','2017','silver','4','100','Can seat 7 people');

INSERT INTO cars
VALUES('9','sedan','Pontiac','Sunbird','1992','white','4','15','Starts most of the time');

INSERT INTO cars
VALUES('10','sedan','Honda','Civic','2004','silver','2','30','Very reliable');

INSERT INTO cars
VALUES('11','suv','Honda','Civic','1996','black','1','20','You could not break this car if you tried');

INSERT INTO cars
VALUES('12','sport','Ford','Mustang','2015','red','6','75','What a stang it is');


#add rental dates for the cars
INSERT INTO rental_dates
VALUES('1','1','2016-9-16','2016-11-15');

INSERT INTO rental_dates
VALUES('2','2','2016-9-22', '2016-9-23');

#Add users to the database
INSERT INTO users(id, firstname,lastname,username,pswrd)
VALUES('2','Connor', 'Kirk','username','fe7ea6ec01e0343fa272ece3f2b92a39','6663332222','connor@ryde.com');

INSERT INTO users
VALUES('3', 'Mehdi', 'Allahyari', 'mehdi', '957c25972e8c5c305194961077b8eee2', '7065421234', 'mehdi@uga.edu');

INSERT INTO users
VALUES('4','Sahi', 'Nimmakayalu', 'sahi', '5f4dcc3b5aa765d61d8327deb882cf99', '6662221111', 'sahi@ryde.com');

INSERT INTO users
VALUES('5','Kanye','West','crazyboi420','098afd6b19e00dee1fe0a468d3b7a74b', '567890987', 'yeezus@psychward.com');

INSERT INTO users
VALUES('6','Joe','Biden','vpbiden','6aeb82af6c1239dafed195541881776a','1011011111', 'joe@whitehouse.gov');

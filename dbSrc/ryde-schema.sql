#
#   Author: John Porter
#   The following is a schema to create applicable tables in a database for the Ryde system
#   Ryde is a car rental system developed for the term project of CSX300 at The University of Georgia
#   The contributers to the Ryde system are : John Porter YOUR NAMES HERE
#
#
# TODO: 
#
#
DROP TABLE IF EXISTS images;
DROP TABLE IF EXISTS rental_dates;
DROP TABLE IF EXISTS cars;
DROP TABLE IF EXISTS owners;
DROP TABLE IF EXISTS users;

#
#   Table for a user looking to rent a car
#
CREATE TABLE users(
    id  INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    firstName   VARCHAR(255),
    lastName    VARCHAR(255),
    username    VARCHAR(255) UNIQUE,
    pswrd       VARCHAR(255),
    phone       VARCHAR(255),
    email       VARCHAR(255)
    #
    #other things here
    #

)ENGINE=InnoDB;



#
#   Table for a car
#
CREATE TABLE cars(
    id  INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    carType VARCHAR(255),
    make    VARCHAR(255),
    model   VARCHAR(255),
    carYear INT UNSIGNED,
    color   VARCHAR(255),
    ownerID   INT UNSIGNED NOT NULL,
    price   INT UNSIGNED NOT NULL,
    description VARCHAR(1024),
    
    
    FOREIGN KEY (ownerID) REFERENCES users(id)
)ENGINE=InnoDB;


#
#   Table for a set of rental dates a car might be booked for
#
CREATE TABLE rental_dates(
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    carID   INT UNSIGNED NOT NULL,
    startDate DATE,
    endDate DATE,
    
    FOREIGN KEY (carID) REFERENCES cars(id)
)ENGINE=InnoDB;


#
#   Table for car images
#
CREATE TABLE images(
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    carID INT UNSIGNED NOT NULL,
    image MEDIUMBLOB,
    
    FOREIGN KEY (carID) REFERENCES cars(id)
)ENGINE=InnoDB;

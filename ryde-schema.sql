#
#   Author: John Porter
#   The following is a schema to create applicable tables in a database for the Ryde system
#   Ryde is a car rental system developed for the term project of CSX300 at The University of Georgia
#   The contributers to the Ryde system are : John Porter YOUR NAMES HERE
#
    
#
#   Table for a car
#
CREATE TABLE cars(
    id  INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    make    VARCHAR(255),
    model   VARCHAR(255),
    carYear INT UNSIGNED,
    color   VARCHAR(255),
    ownerID   INT UNSIGNED NOT NULL;
    
    FOREIGN KEY (ownerID) REFERENCES owners(id)
)ENGINE=InnoDB;

#
#   Table for an owner of a car available for rental
#
CREATE TABLE owners(
    id  INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    firstName   VARCHAR(255),
    lastName    VARCHAR(255),
    #
    #other things here
    #

)ENGINE=InnoDB;

#
#   Table for a user looking to rent a car
#
CREATE TABLE users(
    id  INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    firstName   VARCHAR(255),
    lastName    VARCHAR(255),
    #
    #other things here
    #

)ENGINE=InnoDB;
CREATE TABLE User (
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    dateOfBirth DATE NOT NULL,
    PRIMARY KEY (`username`)
);

CREATE TABLE Movie (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    posterImage VARCHAR(255) NOT NULL,
    releaseDate DATE NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE Actor (
    id INT NOT NULL AUTO_INCREMENT,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    dateOfBirth DATE NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE Comment (
    id INT NOT NULL AUTO_INCREMENT,
    comment VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    username VARCHAR(255) NOT NULL,
    movieId INT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`username`) REFERENCES User (`username`),
    FOREIGN KEY (`movieId`) REFERENCES Movie (`id`)
);

CREATE TABLE Cast (
    id INT NOT NULL AUTO_INCREMENT,
    characterName VARCHAR(255) NOT NULL,
    actorId INT NOT NULL,
    movieId INT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`actorId`) REFERENCES Actor (`id`),
    FOREIGN KEY (`movieId`) REFERENCES Movie (`id`)
);

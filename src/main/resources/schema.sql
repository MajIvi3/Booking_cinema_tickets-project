
//Defines the database at server startup
CREATE TABLE  Billett
(
    id INTEGER AUTO_INCREMENT NOT NULL,
    film VARCHAR (255) NOT NULL,
    navn VARCHAR (255) NOT NULL,
    etternavn VARCHAR (255) NOT NULL,
    telefon VARCHAR (255) NOT NULL,
    epost VARCHAR (255) NOT NULL,
    antal VARCHAR (255) NOT NULL,
    PRIMARY KEY (id)

);
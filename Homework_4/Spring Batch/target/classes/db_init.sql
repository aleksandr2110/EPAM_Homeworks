CREATE SCHEMA main;
CREATE TABLE users(
    firstName VARCHAR(128),
    lastName VARCHAR(128),
    balance FLOAT,
    email VARCHAR(128)
);
INSERT INTO users (firstName, lastName, balance, email)
VALUES (
  'Aleksandr', 'Olejnik', 8.45, 'utel2010@i.ua'
);
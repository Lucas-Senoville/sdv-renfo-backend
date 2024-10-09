CREATE TABLE student
(
    id        SERIAL PRIMARY KEY,
    firstname VARCHAR(100),
    lastname  VARCHAR(100)
);

CREATE TABLE teacher
(
    id        SERIAL PRIMARY KEY,
    firstname VARCHAR(100),
    lastname  VARCHAR(100)
);
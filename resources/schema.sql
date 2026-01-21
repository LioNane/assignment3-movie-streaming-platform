CREATE TABLE films(
    id INT PRIMARY KEY,
    name VARCHAR(50) UNIQUE,
    duration INT NOT NULL,
    rating FLOAT NOT NULL
);
CREATE TABLE series(
    id INT PRIMARY KEY,
    name VARCHAR(50) UNIQUE,
    rating FLOAT NOT NULL
);
CREATE TABLE episodes(
    id INT PRIMARY KEY,
    name VARCHAR(50) UNIQUE,
    duration INT NOT NULL,
    series_id INT NOT NULL,

    FOREIGN KEY (series_id) REFERENCES series(id)
);

INSERT INTO films VALUES (1,'Inception',148,8.8);
INSERT INTO series VALUES (1,'Breaking Bad',9.5);
INSERT INTO episodes VALUES (1,'Pilot',58,1);
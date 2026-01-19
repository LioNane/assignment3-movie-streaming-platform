A. Project Overview

Purpose of API is to help users interact with streaming platform.
There are abstract classes, its subclasses, another class with composition of subclass and repository classes used for JDBC.

B. OOP Design Documentation

Content is abstract class, Film and Series are subclasses of Content with overriden methods.
Interfaces Validatable implemented in every class with method validate() to check validation and Playable in classes Film, Series, Episode with play() to "play" them.
Episode is another class with composition of Series.
Polymorphism with method getContentType() returning type of subclass and countDuration(): in Film returns duration in minutes, in Series returns sum of durations of episodes.

C. Database Description

Schema: films(id INT PK, name VARCHAR UNIQUE, duration INT NOT NULL, rating FLOAT NOT NULL) 
series(series_id INT PK, name VARCHAR UNIQUE, rating FLOAT, NOT NULL) 
episodes(id INT PK, name VARCHAR UNIQUE, duration INT NOT NULL, series_id INT NOT NULL FK)

INSERT INTO films(1, "Inception", 148, 7.0);

D. Controller

CRUD operations allows users to interact with DB, create entities, get entities, update and delete them
Examples of requests/responses on screenshots

E. Instructions to Compile and Run

java Main

Reflection Section

I learned how to work with JDBC and exceptions.
I faced challenges with try-catch because it is new feature for me.
Benefits of JDBC is connection with DBs through Java and convenient manipulation with it. Multi-layer design increase understanding of program purpose and simplify it.

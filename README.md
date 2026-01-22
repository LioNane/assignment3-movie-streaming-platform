A. Project Overview

Purpose of API is to help users interact with streaming platform, manage content.
There are abstract classes, its subclasses, another class with composition of subclass and repository classes used for JDBC.

B. OOP Design Documentation

Content is abstract class, Film and Series are subclasses of Content with overriden methods.
Interfaces Validatable implemented in every class with method validate() to check validation and Playable in classes Film, Series, Episode with play() to "play" them.
Episode is another class with composition of Series.
Polymorphism with method displayInfo() returning type of subclass and countDuration(): in Film returns duration in minutes, in Series returns sum of durations of episodes.

C. Database Description

Schema: films(id INT PK, name VARCHAR UNIQUE, duration INT NOT NULL, rating FLOAT NOT NULL) 
series(series_id INT PK, name VARCHAR UNIQUE, rating FLOAT, NOT NULL) 
episodes(id INT PK, name VARCHAR UNIQUE, duration INT NOT NULL, series_id INT NOT NULL FK)

D. Controller

CRUD operations allows users to interact with DB, create entities, get entities, update and delete them
Request delete episode with id = 2, result: episode was deleted.
Request create with name = "", result: validation error.

E. Instructions to Compile and Run

java Main + PostgreSQL + JDBC

Reflection Section

I learned how to work with JDBC and exceptions, how to make class repositories and services.
I faced challenges with try-catch because it is a new feature for me.
Benefits of JDBC is connection with DBs through Java and convenient manipulation with it.
Multi-layer design allows to manipulate with data on one level (repository) and implement some rules for data using on another level (service).

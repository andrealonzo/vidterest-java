# URL Shortener Microservice JAVA implementation

## Overview

This is my JAVA implementation of the URL Shortener Microservice basejump for Free Code Camp.

A running version of this program can be found at https://urlshortener-java.herokuapp.com/

## Build

To build the project, you must have Maven 3 installed.

Run the following command to build the project

    $ mvn clean install 

## Run

Set the DATABASE_URL environment variable.  The format should be similar to this 

    postgres://<db_username>:<db_password>@localhost:5432/testdb

Run the database creation script found in 

    /scripts/db_creation.txt

Run the following command to start the server 

    $ mvn jetty:run

## Features

| Features 
|:---------         
| Java           
| Maven  
| Jackson JSON parser
| Jetty         
| Servlets 
| Postgres DB 

## License

MIT License. [Click here for more information.](LICENSE.md)

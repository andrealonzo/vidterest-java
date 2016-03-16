# Video Pinterest Application JAVA implementation

## Overview

This is my implementation of the Pinterest clone application, which is project for Free Code Camp.  The front end is written in Javascript and React.  The back end is written in Java.

A running version of this program can be found at https://vidterest-java.herokuapp.com/

## Build

To build the project, you must have Maven 3, node, and webpack installed.

Run the following command to build the project

    $ mvn clean install 

## Run

Create configuration file at

	/src/main/resources/config.properties
	
You will see a sample configuration template in 

	/src/main/resources/config.properties.sample

Run the database creation script found in 

    /scripts/db_creation.txt

Run the following command to start the server 

    $ mvn jetty:run
    
To view the application, open your browser and go to:
	
	http://localhost:8080

## Features

| Backend Features 
|:---------         
| Java           
| Maven  
| Jackson JSON parser
| Jetty         
| Servlets 
| Postgres DB 
| Hibernate

| Frontend Features 
|:---------         
| Javascipt           
| React  
| Flux
| Webpack

## License

MIT License. [Click here for more information.](LICENSE.md)

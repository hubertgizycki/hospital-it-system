# hospital-it-system
> Java & Spring Framework application for hospital IT system management with simple Spring Web interface for user operation of the system.

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Status](#status)
* [Contact](#contact)

## General info
The project was created in order to consolidate the knowledge of learning Java programming, databases and the use of the technologies listed below.

## Technologies
Project is created with:
* Java - version 1.8
* Spring - version STS 4.8.0.
* Spring MVC
* Spring Data JPA
* Maven
* MySQL
* Lombok
* Thymeleaf
* HTML, CSS

## Setup
To run this project you will need:
* MySQL Server
* Maven

It is very important that there is a root user in our database, without a password, but if a password is set, enter it in the _application.properties_ -> [here](https://github.com/hubertgizycki/hospital-it-system/blob/master/src/main/resources/application.properties).

Then follow these steps:

1. Clone repository
```
git clone https://github.com/hubertgizycki/hospital-it-system
```
2. Select directory to your project folder e.g.
```
cd "C:\Users\Dell\hospital-it-system"
```
3. Run project
```
mvn spring-boot:run
```
4. Go to your browser and start here
```
http://localhost:8080/
```

## Features
* Create new data (Patient, Doctor, Department)
* Reading and printing data: single by id and all objects on the list (Patient, Doctor, Department, Nurse)
* Update information about the objects (Patient, Doctor, Department)
* Deletion of data (Patient, Doctor, Nurse)
* forms of protections in the case of data deletion

## Status
Project is: _finished_

## Contact
Created by [@hubertgizycki](https://github.com/hubertgizycki) - feel free to contact me!

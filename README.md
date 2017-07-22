# _Hair Salon Management Software_

#### _Brief Description: Program executes various tasks relating to tracking stylists and clients to make their day to day life easier, July 14, 2017_

#### By _**Mara Timberlake**_

## Description
_This program, used by managers to execute various salon-related tasks, includes:_
* _Lists out all of the salon's stylists, select a stylist, see their details, and see a list of all clients that belong to that stylist._
* _Adds new stylists, and new clients to a specific stylist._
* _Updates stylist's and client's details._
* _Delete a stylist, and reroute that stylist's clients to an 'other' stylist category._
* _Delete a client._

## Specifications

* _Program displays homepage details of the salon_
* _User (manager) can view all listed stylists and clients, and add or delete a member from either category._
* _Displays the updated information upon submission._
* _Example:_

|Behavior|Input|Output|
|---|---|---|
|Comb as You Are - Add New Stylist|Inigo Montoya|Stylist : Inigo Montoya|

## What's included
Within the repository you'll find the following directories and files:

```
java-hair-salon/
├── src/
│    └── main/
│    │    └── java/
|    │    │     └── App.java
|    │    │     └── Client.java
|    │    │     └── DB.java
|    │    │     └── Stylist.java
|    │    │     └── VelocityTemplateEngine.java
|    |    └── resources/
|    |          └──public/
|    |               └──styles.css
|    |          └──templates/
|    |               └──client.vtl
|    |               └──index.vtl
|    |               └──layout.vtl
|    |               └──stylist.vtl
|    └── test/
│         └── java/
|               └── ClientTest.java
|               └── DatabaseRule.java
|               └── StylistTest.java
├── .gitignore
├── build.gradle
└── README.md
```

## Setup/Installation Requirements
* _Install gradle on your device_
* _LOCAL: Go to Terminal_
* _Clone this repository:_
```
$ cd ~/Desktop
$ git clone https://github.com/Epicodus-MT/hair-salon
$ cd hair-salon
```
* _Run Postgres with terminal command:_
```
$ postgres
```
* _Open a new tab in terminal by pressing [command ⌘] + [T]_
* _In the new tab, create 'sales_tracker' database:_
```
$ psql
$ CREATE DATABASE hair_salon;
$ \c hair_salon;
$ CREATE TABLE stylists (id SERIAL PRIMARY KEY, name varchar);
$ CREATE TABLE clients (id SERIAL PRIMARY KEY, name varchar, details varchar, stylistId int);
$ CREATE DATABASE hair_salon_test WITH TEMPLATE hair_salon;
```
* _Return to original tab where repository was cloned and run gradle:_
```
$gradle run
```
* _Open browser window:_
```
localhost:4567
```

## Known Bugs

_No known bugs at this time_

## Support and contact details

_For questions or feedback, please notify Mara Timberlake at maratimberlake@msn.com_

## Technologies Used

_Languages used include Java. IDE used - Atom_

### License

*This software runs under the MIT license*

Copyright (c) 2017 **_Mara Timberlake_**

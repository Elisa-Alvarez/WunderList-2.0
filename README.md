# Wunderlist 2.0/backend

## Java 14 w/ Spring Boot Framework

Database Table Layout

![Database-image](wunderlist2.0.PNG)

## Users
Method | Endpoint | Description | Required Data
--- | --- | --- | ---
POST | /users/sign-up | Creates a new user account | `'username', 'password',' email', 'firstname', 'lastname'` 
POST | /login | Grants an acesstoken to the user | `'username', 'password'` 
GET | /users/all | Returns all Users
GET | /users/user/:userid | Returns that User by userid
GET | /users/:username | Returns a User by the exact username
GET | /username/search/:letter | Returns all users containing those letters.
PUT | /update/:userid | Updates/Changes an entire User | `username, password, email, firstname, lastname` -- All Strings
PATCH | /users/patchuser/:userid | Changes a specific field in User object | Send the string you would like to change `"username", "password", "email", "firstname", "lastname"` 
DELETE | /users/:userid | Delete by userid

## Categories
Method | Endpoint | Description | Required Data
--- | --- | --- | ---
GET | /categories | Returns all Todo Lists and their associated Items
GET | /categories/:category | Returns a Category Arraylist by categoryid and Items is an array nested inside.
POST | /categories/:userid | Creates a new Category for that specific User | `"title"`
PUT | /categories/:categoryid/:title | Updates/Changes the title of an existing Todo List
DELETE | /categories/:categoryid | Deletes a Todo List and it's associated Items

## Items
Method | Endpoint | Description | Required Data
--- | --- | --- | ---
GET | /items/itemlist | Returns a list of all Items
GET | /items/:itemid | Returns item by id
POST | /items/:categoryid | Creates a new Item for a Category by id | `name, description, date, repeat, done, isShared = false(set false as default)`"yyyy-MM-dd" is date format it's a string, returned into Date object due. isShared is so that way you can alter other Users editing of a shared items.
PUT | /update/:itemid | Updates/changes an entire Item | `name, description, date, repeat, done, isShared` 
PATCH | /items/:itemid | Updates one feild in Item | One of `name, description, date, repeat, done,isShared`.
DELETE | items/:itemid | Delete an Item





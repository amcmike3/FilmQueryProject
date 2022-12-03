# FilmQueryProject


# Description:
This program follows several programming frameworks including but not limited to Data Access Object (DAO) pattern and Object Relational Mapping (ORM). This Project utilizes JDBC and Maven to connect to a database that contains several tables. The Program accesses the database using a DAO and following ORM, puts the data retrieved into their respective object either actor or film. The program will loop giving the user several options of dynamic SQL statements based on user input. 


# Tech Used:
- Maven
- Java
- JDBC
- Object Relational 

# SQL Statements:
- SELECT id, first_name, last_name FROM actor WHERE id = ?;
<br/>
Will retrieve actor information based on the actor id chosen by user.

- SELECT * FROM film JOIN film_actor ON film.id = film_actor.film_id WHERE actor_id = ?;
<br/>
Will retrieve film information based on a actor id chosen by user.

SELECT *" + " FROM film " + " JOIN language ON language.id = film.language_id WHERE film.id = ?
<br/>
Will find film by id


# Lessons Learned:
- Object Relational Mapping
- Using Statement objects as opposed to PreparedStatements will make the program vulnerable to SQL Injection attacks. This is because Statement allows string concatenation where PreparedStatments do not. 

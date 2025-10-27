# librarymanagementsystem
 
validate inputs,
test cases for search,
add loggers in services,    
coverages ok.


create -	Drops existing tables and creates new ones from your entities. Existing data is lost.
create-drop -	Same as create, but also drops tables when app stops.
update -	Tries to update existing tables to match your entities. Adds new columns, keeps existing data.
validate -	Checks that the tables match your entities. Throws error if mismatch.
none -	Does nothing to the database structure.
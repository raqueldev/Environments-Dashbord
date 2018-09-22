[Environments Dashboard] pretends to be the pet project that I will use to improve the Kotlin skills.

 Disclaimer
 __________

 * This project is on going, and there is no version ready yet. If you arrived here looking for a final version,
 sorry! it is not the case. Come later on in the future :P
 
 
 Documentation
 _____________
 
 * To Run this project when this is ready, it will be needed a sqllite db that it is included on this project database Folder
 * Go to https://www.sqlite.org/index.html and download the client for sqllite. 
 * To run sqllite client everytime, add the path as environment variable on the bashrc file.
 
 sudo vi /etc/bashrc
 PATH=/c/sqlite:$PATH
 
 * Test that everything was set up: sqlite3 -help
 * Now you can go to the database folder in the project and run `sqlite3 environments_monitoring.db` on the terminal to
 play around the db.
 
 * To configure the Database into IntelliJ Idea, Go to View > Tool Windows > DB Browser. (If this does not exist, install first the DB Browser plugin)
 
 

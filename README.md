javaRFID
========
The target of this project is to create a base for simple access management system.
User identifies oneself by entering an RFID tag to an RFID reader by Phidgets Inc.
The system then checks from database if the user can be granted an access. If allowed,
the IO port in the reader is set to 1. After timeout the input resets to 0.

Done:
RFID reading
Database creation
Writing data to database

To-Do
Reading data from database
User authentication
IO-state management
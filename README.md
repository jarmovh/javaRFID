javaRFID
========
The target of this project is to create a base for simple access management system.
User identifies oneself by entering an RFID tag to an RFID reader by Phidgets Inc.
The system then checks from database if the user can be granted an access. If allowed,
the IO port in the reader is set to 1. After timeout or similar event the input resets to 0.

The RFID functionality is derivative work from the code examples by Phidgets Inc., the original
code is released under Creative Commons Attribution 2.5 Canada License.

The Sqlite functionality uses SQLite JDBC-library, developed by Xerial. SQLite JDBC is
licensed under Apache License version 2.0

Rest of this software is licensed under the MIT License.



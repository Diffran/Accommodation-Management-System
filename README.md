# Accommodation-Management-System

## Description 🏨
The Accommodation Management System is designed to efficiently manage establishments and their various types of accommodations. Each Establishment can have multiple Accommodations, which include Rooms, Campsites, and Bungalows.

**Establishments** 🏨 store essential data such as their unique code, name, description, star rating, address.

Each establishment hosts different types of accommodations, each with its specific attributes:

**Rooms** 🛏️ include features such as room type, number of beds, and minibar availability.

**Campsites** 🏕️ specify amenities like shade, electricity, and water.

**Bungalows** 🏡 have attributes such as maintenance date and whether they are equipped with a kitchen.

The system utilizes **Java Persistence API (JPA)** for object-relational mapping, allowing seamless integration with a **PostgreSQL** database. Users can perform a variety of operations, including adding, modifying, or deleting establishments and accommodations, as well as querying specific data based on criteria like availability, capacity, and amenities.

## Installation
* 1- git clone https://github.com/yourusername/Accommodation-Management-System.git
* 2- Ensure you have **PostgreSQL** installed and running
* 3- Create a database named **jpa2425s1** and set up the user credentials **(user: ioc, password: ioc)** i **grant all privileges** a la database al usuari ioc.
* 4- Modify the connection details in **src/main/resources/META-INF/persistence.xml** to match your database configuration if needed.
* 5- build in your IDE
* 6- Use the Test to ensure it works

## Used Technologies
* **Java 21**
* **JPA**
* **PostgreSQL**
* **EclipseLink**
* **Maven**

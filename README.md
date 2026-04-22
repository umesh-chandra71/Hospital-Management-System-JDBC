# Hospital Management System – JDBC Implementation 🏥

**Developer:** Umeshchandra (237R1A6771) – CMR Technical Campus  

A simple **Hospital Management System** built in **Java** using **JDBC** and **MySQL** to manage doctors and patients efficiently in a relational database. This project demonstrates real-time database operations using Java and provides hands-on experience with backend connectivity.

---

## Technologies Used 🛠️

- **Java** – Core language for application logic  
- **JDBC** – Java Database Connectivity to interact with MySQL  
- **MySQL** – Relational database to store doctor and patient records  
- **Scanner (Java)** – For console-based user input  

---

## Features ✨

- Add new doctors and patients.  
- View all doctors and patients.  
- Retrieve doctor/patient details by ID for appointment confirmations.  
- Demonstrates basic CRUD (Create & Read) operations via JDBC.  

---

## Database Setup 🗄️

**MySQL Tables:**  

```sql
CREATE TABLE doctors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    specialization VARCHAR(100) NOT NULL
);

CREATE TABLE patients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(10) NOT NULL
);

# Enotes Spring Boot Application

This is a Spring Boot-based application designed to manage user profiles and e-notes. It includes secure user registration and email validation functionalities, along with persistent storage for user-generated notes.

## Features

- 🧑‍💻 User Registration and Profile Management
- 🔐 Secure Password Handling using BCrypt
- ✅ Email Uniqueness Check Before Registration
- 📝 E-Notes Storage Functionality
- 📦 Spring Data JPA Integration
- 🔐 Spring Security for Authentication

## Technologies Used

- Java 17+
- Spring Boot
- Spring Data JPA
- Spring Security
- MySQL/PostgreSQL (or any JPA-supported DB)
- Maven
- Hibernate
- Jakarta Servlet API

## Getting Started

### Prerequisites

- Java JDK 17 or later
- Maven
- MySQL/PostgreSQL database

### Installation

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-username/enotes-app.git
   cd enotes-app

2. **Configure the Database Update the application.properties file with your database configuration**
    ```bash
    spring.datasource.url=jdbc:mysql://localhost:3306/enotes
    spring.datasource.username=root
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update

3. **Run the Application**
    ```bash
    mvn spring-boot:run


### Contributing
    Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.
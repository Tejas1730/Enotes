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

    
## Architecture
   ```mermaid
   flowchart TB

   %% Client Zone
   subgraph Client
       Browser["Web Browser"]
   end

   %% Server Zone
   subgraph "Server (Spring Boot Application)"
       EnotesApp["EnotesSpringBootApplication"]

       subgraph "Presentation Layer"
           Views["Thymeleaf Views"]
           StaticAssets["Static Assets (CSS/JS/Images)"]
           subgraph Controllers
               HomeCtrl["HomeController"]
               UserCtrl["UserController"]
           end
       end

       subgraph "Security Layer"
           SecurityCfg["SecurityConfig"]
           CustomUDS["CustomUserDetailsService"]
           CustomUser["CustomUser"]
       end

       subgraph "Service Layer"
           UserSvc["UserService"]
           UserSvcImpl["UserServiceImpl"]
       end

       subgraph "Data Access Layer"
           UserRepo["UserRepository"]
           NoteRepo["NoteRepository"]
           subgraph Entities
               UserEntity["User Entity"]
               NoteEntity["Note Entity"]
           end
       end

       Database["Relational Database"]
       SMTP["External SMTP Service"]
       AppConfig["application.properties"]
   end

   %% Connections
   Browser --> SecurityCfg
   SecurityCfg --> CustomUDS
   CustomUDS --> UserRepo
   SecurityCfg --> HomeCtrl
   SecurityCfg --> UserCtrl

   HomeCtrl --> Views
   HomeCtrl --> StaticAssets
   UserCtrl --> Views
   UserCtrl --> StaticAssets

   HomeCtrl --> UserSvc
   UserCtrl --> UserSvc

   UserSvc --> UserSvcImpl
   UserSvcImpl --> UserRepo
   UserSvcImpl --> NoteRepo

   UserRepo --> Database
   NoteRepo --> Database

   EnotesApp --> SecurityCfg
   EnotesApp --> HomeCtrl
   EnotesApp --> UserCtrl
   EnotesApp --> UserSvcImpl
   EnotesApp --> UserRepo
   EnotesApp --> NoteRepo

   AppConfig --> EnotesApp

   SMTP -.-> UserSvcImpl

   %% Click Events
   click EnotesApp "https://github.com/tejas1730/enotes/blob/main/src/main/java/com/enotes/EnotesSpringBootApplication.java"
   click Views "https://github.com/tejas1730/enotes/tree/main/src/main/resources/templates/"
   click StaticAssets "https://github.com/tejas1730/enotes/tree/main/src/main/resources/static/"
   click HomeCtrl "https://github.com/tejas1730/enotes/blob/main/src/main/java/com/enotes/controller/HomeController.java"
   click UserCtrl "https://github.com/tejas1730/enotes/blob/main/src/main/java/com/enotes/controller/UserController.java"
   click SecurityCfg "https://github.com/tejas1730/enotes/blob/main/src/main/java/com/enotes/config/SecurityConfig.java"
   click CustomUDS "https://github.com/tejas1730/enotes/blob/main/src/main/java/com/enotes/config/CustomUserDetailsService.java"
   click CustomUser "https://github.com/tejas1730/enotes/blob/main/src/main/java/com/enotes/config/CustomUser.java"
   click UserSvc "https://github.com/tejas1730/enotes/blob/main/src/main/java/com/enotes/service/UserService.java"
   click UserSvcImpl "https://github.com/tejas1730/enotes/blob/main/src/main/java/com/enotes/service/UserServiceImpl.java"
   click UserRepo "https://github.com/tejas1730/enotes/blob/main/src/main/java/com/enotes/repository/UserRepository.java"
   click NoteRepo "https://github.com/tejas1730/enotes/blob/main/src/main/java/com/enotes/repository/NoteRepository.java"
   click UserEntity "https://github.com/tejas1730/enotes/blob/main/src/main/java/com/enotes/entity/User.java"
   click NoteEntity "https://github.com/tejas1730/enotes/blob/main/src/main/java/com/enotes/entity/Note.java"
   click AppConfig "https://github.com/tejas1730/enotes/blob/main/src/main/resources/application.properties"


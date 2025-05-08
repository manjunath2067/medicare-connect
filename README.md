# Medicare Connect

Medicare Connect is a Spring Boot-based application designed to manage healthcare-related operations such as managing doctors, patients, appointments, and departments. The project uses a RESTful API architecture and integrates various technologies to provide a robust and scalable solution.

## Features
- User management (Doctors, Patients, and Admins)
- Department management
- Appointment scheduling and management
- Authentication and authorization
- RESTful APIs for CRUD operations

## Tech Stack
### Backend
- **Java**: Primary programming language.
- **Spring Boot**: Framework for building the application.
    - Spring Data JPA: For database interactions.
    - Spring Security: For authentication and authorization.
- **Maven**: Dependency management and build tool.
- **Jakarta Persistence API (JPA)**: For ORM (Object-Relational Mapping).
- **Lombok**: To reduce boilerplate code for model classes.
- **Jackson**: For JSON serialization and deserialization.

### Database
- **MySQL**: Relational database for storing application data.

### Tools
- **IntelliJ IDEA**: IDE for development.
- **Postman**: For testing REST APIs.

## Project Structure
```
src/main/java/com/example/medicare/connect/
├── controller/       # REST controllers for handling API requests
├── model/            # Entity classes representing database tables
├── repository/       # JPA repositories for database operations
├── service/          # Business logic and service layer
└── application/      # Main application entry point
```

## Prerequisites
- Java 17 or higher
- Maven 3.8+
- MySQL 8.0+
- IntelliJ IDEA (recommended)

## Setup Instructions
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd medicare-connect
   ```

2. Configure the database:
    - Create a MySQL database named `medicare_connect`.
    - Update the database connection details in `src/main/resources/application.properties`:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/medicare_connect
      spring.datasource.username=<your-username>
      spring.datasource.password=<your-password>
      spring.jpa.hibernate.ddl-auto=update
      ```

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

5. Access the application:
    - The application will be available at `http://localhost:8080`.

## API Endpoints
### Authentication
- `POST /api/auth/login`: Login with email and password.

### Users
- `GET /api/users`: Retrieve all users.
- `POST /api/users`: Create a new user.

### Doctors
- `GET /api/doctors`: Retrieve all doctors.
- `POST /api/doctors`: Add a new doctor.

### Patients
- `GET /api/patients`: Retrieve all patients.
- `POST /api/patients`: Add a new patient.

### Departments
- `GET /api/departments`: Retrieve all departments.
- `POST /api/departments`: Add a new department.

### Appointments
- `GET /api/appointments`: Retrieve all appointments.
- `POST /api/appointments`: Schedule a new appointment.

## Dependencies
- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Boot Starter Security
- Lombok
- MySQL Connector
- Jackson Databind

## Author
Developed by [manjunathbt2067@gmail.com].
```

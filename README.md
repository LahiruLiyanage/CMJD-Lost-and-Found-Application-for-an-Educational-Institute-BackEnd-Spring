# Lost and Found Application - Backend

A comprehensive REST API backend application for managing lost and found items in an educational institute, built with Spring Boot and MySQL.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Database Configuration](#database-configuration)
- [API Documentation](#api-documentation)
- [Project Structure](#project-structure)
- [Usage Examples](#usage-examples)
- [Security](#security)
- [Contributing](#contributing)
- [License](#license)

## 🎯 Overview

The Lost and Found Application is a backend REST API system designed to help educational institutes manage lost and found items efficiently. Users can report lost items, register found items, and submit claims for items they believe belong to them. The system includes role-based access control with Admin, Staff, and User roles.

## ✨ Features

### Core Functionality
- **Item Management**: Create, read, update, and delete lost/found items
- **User Management**: User registration, authentication, and profile management
- **Claim System**: Users can submit claims for items with approval workflow
- **Search & Filter**: Advanced search functionality for items
- **Status Tracking**: Track item status (LOST, FOUND, CLAIMED)

### Security Features
- **JWT Authentication**: Secure token-based authentication
- **Role-based Authorization**: Admin, Staff, and User roles with different permissions
- **Password Encryption**: BCrypt password hashing
- **Input Validation**: Comprehensive request validation

### Additional Features
- **Audit Logging**: Track all system activities
- **RESTful API**: Clean and well-documented API endpoints
- **Error Handling**: Comprehensive error handling and meaningful responses
- **Database Integration**: Seamless MySQL integration with JPA

## 🛠 Tech Stack

- **Framework**: Spring Boot 3.x
- **Security**: Spring Security + JWT
- **Database**: MySQL 8.0+
- **ORM**: Spring Data JPA / Hibernate
- **Build Tool**: Maven
- **Documentation**: Swagger/OpenAPI (optional)
- **Testing**: JUnit 5, Mockito
- **Other**: Lombok, Bean Validation

## 📋 Prerequisites

Before running this application, make sure you have the following installed:

- **Java 17** or higher
- **Maven 3.6+**
- **MySQL 8.0+**
- **IntelliJ IDEA Ultimate** (recommended) or any Java IDE
- **Git**

## 🚀 Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/lost-and-found-app-backend.git
cd lost-and-found-app-backend
```

### 2. Configure Database

Create a MySQL database:

```sql
CREATE DATABASE lost_and_found_db;
CREATE USER 'lostfound_user'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON lost_and_found_db.* TO 'lostfound_user'@'localhost';
FLUSH PRIVILEGES;
```

### 3. Update Application Properties

Edit `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/lost_and_found_db
spring.datasource.username=lostfound_user
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.format_sql=true

# JWT Configuration
jwt.secret=mySecretKeyForJWTTokenGenerationAndValidation
jwt.expiration=86400000

# Server Configuration
server.port=8080

# Logging Configuration
logging.level.lk.ijse.cmjd.lostandfoundappbackend=DEBUG
logging.level.org.springframework.security=DEBUG
```

### 4. Build and Run

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

Or run directly from your IDE by executing the main class: `LostAndFoundAppBackendApplication.java`

### 5. Verify Installation

The application will start on `http://localhost:8080`. You can verify it's running by accessing:
- Health check: `GET http://localhost:8080/actuator/health` (if actuator is enabled)
- API base: `GET http://localhost:8080/api/`

## 🗄 Database Configuration

### Entity Relationships

```
User (1) -----> (*) Item
User (1) -----> (*) Request
Item (1) -----> (*) Request
```

### Default Data

The application will automatically create tables on first run. You may want to insert some default admin users:

```sql
INSERT INTO users (username, password, email, first_name, last_name, role, created_at, updated_at)
VALUES ('admin', '$2a$10$encoded_password_here', 'admin@institute.edu', 'Admin', 'User', 'ADMIN', NOW(), NOW());
```

## 📚 API Documentation

### Authentication Endpoints

| Method | Endpoint | Description | Access |
|--------|----------|-------------|---------|
| POST | `/api/auth/signup` | Register new user | Public |
| POST | `/api/auth/signin` | User login | Public |

### User Management Endpoints

| Method | Endpoint | Description | Access |
|--------|----------|-------------|---------|
| GET | `/api/users` | Get all users | Admin |
| GET | `/api/users/{id}` | Get user by ID | Admin/Owner |
| PUT | `/api/users/{id}` | Update user | Admin/Owner |
| DELETE | `/api/users/{id}` | Delete user | Admin |

### Item Management Endpoints

| Method | Endpoint | Description | Access |
|--------|----------|-------------|---------|
| GET | `/api/items` | Get all items | All |
| GET | `/api/items/{id}` | Get item by ID | All |
| POST | `/api/items` | Create new item | Authenticated |
| PUT | `/api/items/{id}` | Update item | Owner/Admin |
| DELETE | `/api/items/{id}` | Delete item | Owner/Admin |
| GET | `/api/items/search?keyword={keyword}` | Search items | All |
| GET | `/api/items/status/{status}` | Get items by status | All |

### Request/Claim Endpoints

| Method | Endpoint | Description | Access |
|--------|----------|-------------|---------|
| GET | `/api/requests` | Get all requests | Admin/Staff |
| GET | `/api/requests/{id}` | Get request by ID | Owner/Admin |
| POST | `/api/requests` | Submit claim request | Authenticated |
| PUT | `/api/requests/{id}/approve` | Approve request | Admin/Staff |
| PUT | `/api/requests/{id}/reject` | Reject request | Admin/Staff |

## 📁 Project Structure

```
src/
├── main/
│   ├── java/lk/ijse/cmjd/lostandfoundappbackend/
│   │   ├── LostAndFoundAppBackendApplication.java
│   │   ├── config/
│   │   │   ├── SecurityConfig.java
│   │   │   └── CorsConfig.java
│   │   ├── controller/
│   │   │   ├── AuthController.java
│   │   │   ├── UserController.java
│   │   │   ├── ItemController.java
│   │   │   └── RequestController.java
│   │   ├── dto/
│   │   │   ├── AuthRequest.java
│   │   │   ├── AuthResponse.java
│   │   │   ├── RegisterRequest.java
│   │   │   ├── ItemRequest.java
│   │   │   └── RequestResponse.java
│   │   ├── entity/
│   │   │   ├── User.java
│   │   │   ├── Item.java
│   │   │   └── Request.java
│   │   ├── enums/
│   │   │   ├── UserRole.java
│   │   │   ├── ItemStatus.java
│   │   │   └── RequestStatus.java
│   │   ├── repository/
│   │   │   ├── UserRepository.java
│   │   │   ├── ItemRepository.java
│   │   │   └── RequestRepository.java
│   │   ├── security/
│   │   │   ├── JwtUtils.java
│   │   │   ├── UserPrincipal.java
│   │   │   ├── CustomUserDetailsService.java
│   │   │   └── JwtAuthenticationFilter.java
│   │   └── service/
│   │       ├── UserService.java
│   │       ├── ItemService.java
│   │       └── RequestService.java
│   └── resources/
│       ├── application.properties
│       └── application-dev.properties
└── test/
    └── java/lk/ijse/cmjd/lostandfoundappbackend/
        ├── controller/
        ├── service/
        └── repository/
```

## 🔧 Usage Examples

### Register a New User

```bash
curl -X POST http://localhost:8080/api/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "username": "johndoe",
    "password": "password123",
    "email": "john@example.com",
    "firstName": "John",
    "lastName": "Doe",
    "phoneNumber": "1234567890"
  }'
```

### Login

```bash
curl -X POST http://localhost:8080/api/auth/signin \
  -H "Content-Type: application/json" \
  -d '{
    "username": "johndoe",
    "password": "password123"
  }'
```

### Create a Lost Item

```bash
curl -X POST http://localhost:8080/api/items \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "name": "iPhone 13",
    "description": "Black iPhone 13 with a blue case",
    "category": "Electronics",
    "location": "Library - 2nd Floor",
    "status": "LOST",
    "dateLostFound": "2024-01-15T10:30:00"
  }'
```

### Search Items

```bash
curl -X GET "http://localhost:8080/api/items/search?keyword=iPhone" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## 🔐 Security

### Authentication Flow

1. User registers or logs in with credentials
2. Server validates credentials and generates JWT token
3. Client includes JWT token in Authorization header for protected endpoints
4. Server validates token and processes request

### Role-based Access Control

- **USER**: Can create items, submit claims, view own data
- **STAFF**: All user permissions + approve/reject claims
- **ADMIN**: All permissions + user management

### Security Best Practices

- Passwords are encrypted using BCrypt
- JWT tokens have configurable expiration
- CORS is properly configured
- Input validation on all endpoints
- SQL injection prevention through JPA

## 🧪 Testing

Run the test suite:

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=UserServiceTest

# Run with coverage
mvn test jacoco:report
```

## 🚀 Deployment

### Development Environment

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Production Environment

1. Update `application-prod.properties` with production database settings
2. Build the application: `mvn clean package`
3. Run the JAR file: `java -jar target/lost-and-found-app-backend-1.0.0.jar --spring.profiles.active=prod`

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Code Style Guidelines

- Follow Java naming conventions
- Use meaningful variable and method names
- Add comments for complex business logic
- Write unit tests for new features
- Follow RESTful API design principles

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENCE.txt) file for details.

## 📧 Contact

For any questions or feedback, feel free to reach out:

- Mobile: [+94719607296](tel:+94719607296)
- Email: [lhlahiru95@gmail.com](mailto:lhlahiru95@gmail.com)
- LinkedIn: [LahiruLiyanage](https://www.linkedin.com/in/liyanage-lahiru/)
- GitHub: [@LahiruLiyanage](https://github.com/LahiruLiyanage)
- WebSite: [lahiruliyanage.com](www.lahiruliyanage.com)

## 🎯 Project Status

This project is part of the CMJD (Comprehensive Master Java Developer) Course - Batch 108/109 Assignment-Spring.

**Current Version**: 1.0.0  
**Status**: In Development  
**Last Updated**: May 2025

---

Made with ❤️ by Lahiru Liyanage for CMJD Batch 108/109
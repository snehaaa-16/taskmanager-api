# Task Management API

A production-style Task Management REST API built using Spring Boot. The application provides task management capabilities along with authentication, authorization, validation, caching, scheduling, and asynchronous processing.

## Features

### Task Management

* Create Tasks
* View Tasks
* Update Tasks
* Delete Tasks
* Pagination Support

### Security

* JWT Authentication
* User Registration
* User Login
* Role-Based Access Control (RBAC)
* Password Encryption using BCrypt

### Validation & Error Handling

* Request Validation
* Global Exception Handling
* Custom Error Responses

### Performance & Monitoring

* Redis Caching
* Cache Invalidation
* API Logging
* Error Logging

### Background Processing

* Asynchronous Task Execution
* Scheduled Task Monitoring

## Tech Stack

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA
* MySQL
* Redis
* JWT
* Maven
* Lombok
* Hibernate Validation

## Architecture

```text
Client
   |
   v
Controller Layer
   |
   v
Service Layer
   |
   v
Repository Layer
   |
   v
MySQL Database

Additional Components:
- JWT Authentication
- Redis Cache
- Async Processing
- Scheduler
- Global Exception Handler
```

## Project Structure

```text
src/main/java
├── config
├── controller
├── dto
├── entity
├── exception
├── repository
├── security
├── service
└── util
```

## API Endpoints

### Authentication

| Method | Endpoint       | Description   |
| ------ | -------------- | ------------- |
| POST   | /auth/register | Register User |
| POST   | /auth/login    | Login User    |

### Tasks

| Method | Endpoint    | Description    |
| ------ | ----------- | -------------- |
| POST   | /tasks      | Create Task    |
| GET    | /tasks      | Get All Tasks  |
| GET    | /tasks/{id} | Get Task By ID |
| PUT    | /tasks/{id} | Update Task    |
| DELETE | /tasks/{id} | Delete Task    |

## Sample Task Request

```json
{
  "title": "Learn Spring Boot",
  "description": "Build REST APIs",
  "completed": false
}
```

## Security Flow

1. User registers an account.
2. User logs in using credentials.
3. JWT token is generated.
4. Token is sent in the Authorization header.
5. Protected APIs are accessed using the token.

Example:

```http
Authorization: Bearer <jwt-token>
```

## Configuration

Configure database credentials using environment variables:

```properties
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

Example:

```bash
DB_USERNAME=root
DB_PASSWORD=your_password
```

Redis configuration:

```properties
spring.data.redis.host=localhost
spring.data.redis.port=6379
```

## Running the Application

### Clone Repository

```bash
git clone <repository-url>
```

### Build Project

```bash
mvn clean install
```

### Run Application

```bash
mvn spring-boot:run
```

The application starts on:

```text
http://localhost:8080
```

## Caching

Redis caching is used to improve API response times for frequently accessed task data.

Features:

* Task Retrieval Caching
* Automatic Cache Invalidation
* Reduced Database Calls

## Logging

The application logs:

* Incoming API Requests
* API Responses
* Exceptions
* Validation Failures

## Author

Sneha Jaiswal

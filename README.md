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
* Role-Based Access Control
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

## Author

Sneha Jaiswal

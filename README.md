# ⚙️ Employee Management System — Backend

A robust RESTful backend API built for the **4bitLabs** Employee Management System. This Spring Boot application provides secure, role-based employee, batch, student, and progress management with a MySQL database.

## Tech Stack

- **Java 17** with **Spring Boot 3.5**
- **Spring Data JPA** for ORM and database access
- **MySQL** as the relational database
- **Lombok** for reducing boilerplate code
- **Maven** as the build tool
- **java-dotenv** for environment variable management
- **Spring Boot DevTools** for live reload during development

## Architecture

The project follows a standard **layered architecture**:

```
src/main/java/com/fourbitlabs/employee_management_system/
├── controller/       # REST API controllers
├── entity/           # JPA entity classes
│   ├── User.java             # Base user entity (name, email, password, phone, role, status)
│   ├── TrainerProfile.java   # Trainer-specific profile (specialization, experienceYears, qualification)
│   ├── AnalystProfile.java   # Analyst-specific profile (department)
│   ├── CounsellorProfile.java # Counsellor-specific profile
│   ├── Student.java          # Student entity (linked to counsellor)
│   ├── Batch.java            # Batch entity (linked to trainer & analyst)
│   ├── Assignment.java       # Student-to-Batch assignment mapping
│   └── BatchProgress.java    # Batch progress tracking
├── enums/
│   └── Role.java             # Role enum (ADMIN, ANALYST, TRAINER, COUNSELLOR)
└── response/
    └── ApiResponse.java      # Standardized API response wrapper
```

## Entity Relationships

```
User (1) ──── (1) TrainerProfile / AnalystProfile / CounsellorProfile
Batch (*) ──── (1) TrainerProfile    (trainer_id FK)
Batch (*) ──── (1) AnalystProfile    (analyst_id FK)
Student (*) ── (1) CounsellorProfile (counsellor_id FK)
Assignment ─── Student + Batch       (many-to-many join table)
BatchProgress ─ Batch + Trainer      (progress tracking per batch)
```

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- MySQL 8.0+

### Setup

1. **Create the MySQL database:**
   ```sql
   CREATE DATABASE ems;
   ```

2. **Configure environment variables** (copy from the example file):
   ```properties
   DB_URL=jdbc:mysql://localhost:3306/ems
   DB_USERNAME=your_username
   DB_PASSWORD=your_password
   ```

3. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```

   The API server will start on `http://localhost:8080`.

## Key Features

- **Role-Based Access** — Supports Admin, Analyst, Trainer, and Counsellor roles via the `Role` enum
- **JPA Entity Mapping** — Proper relational mapping with `@ManyToOne` foreign key associations between batches, trainers, analysts, students, and counsellors
- **Auto Timestamps** — `createdAt` and `updatedAt` fields are auto-managed via `@PrePersist` and `@PreUpdate` lifecycle callbacks
- **Environment Config** — Sensitive credentials managed through environment variables using `java-dotenv`
- **Standardized Responses** — All API responses wrapped in a consistent `ApiResponse` format

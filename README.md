# E-Commerce Application 🛒  
### Spring Boot | MySQL | JPA | Spring Security

This is a backend **E-commerce application** built using **Spring Boot**.  
It provides user management, authentication-ready security structure, database persistence using **JPA + MySQL**, and follows a clean layered architecture.

---

## 🚀 Features

- 👤 User registration & management
- 🔐 Password encryption using Spring Security
- 🧾 User validation with Jakarta Validation
- 🗄️ MySQL database integration
- 🔄 JPA & Hibernate ORM
- 📊 Actuator endpoints for monitoring
- 🌐 REST-ready backend architecture
- 🔐 Email / Username based authentication support

---

## 🧑‍💻 Tech Stack

- **Java 17**
- **Spring Boot 3.2**
- **Spring Data JPA**
- **Spring Security**
- **Hibernate**
- **MySQL**
- **Maven**
- **Thymeleaf (view support ready)**
- **Spring Boot Actuator**

---
## 👤 User Entity Details

The `User` entity contains:

- `id`
- `username` (unique)
- `email` (unique)
- `password` (encrypted)
- `firstName`
- `lastName`
- `phoneNumber`
- `role` (USER / ADMIN)
- `createdAt`
- `updatedAt`

Includes:
- Validation annotations (`@NotBlank`, `@Email`, `@Size`)
- Auto timestamps
- Role-based structure ready for authorization

---

## 🔐 Security

- Passwords are encrypted using `PasswordEncoder`
- Implements `UserDetailsService`
- Supports login using **username or email**
- Ready for future JWT / Session-based authentication

---

## 🗄️ Database Configuration

### MySQL Setup

Create database:
```sql
CREATE DATABASE ecommerce;

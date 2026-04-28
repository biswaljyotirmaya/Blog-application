# Blog Application

A full-stack blog management application built with Spring Boot, Thymeleaf, Spring Data JPA, and MySQL. The project allows users to register, log in, create and manage their own blog posts, and lets visitors browse published posts and leave comments on individual articles.

## Overview

This application is designed as a server-rendered blogging platform where authenticated users can manage personal blog content through a dashboard, while general visitors can read posts from the home page. It follows a layered Spring Boot structure with controllers, services, repositories, entities, DTOs, and Thymeleaf templates.

## Features

- User registration and login
- Session-based authentication
- Create new blog posts
- Edit existing posts owned by the logged-in user
- Delete posts owned by the logged-in user
- Public home page listing all blog posts
- Read single post details
- Add comments to posts
- Personal dashboard for managing user-specific posts

## Tech Stack

- Java 17
- Spring Boot
- Spring MVC
- Spring Data JPA
- Thymeleaf
- MySQL
- Maven
- HTML, CSS, and JavaScript

## Project Structure

The application is organized into the following main layers:

- `controller` for request handling and page navigation
- `service` for business logic
- `repo` for database access using Spring Data JPA
- `entity` for database models such as `User`, `Post`, and `Comment`
- `DTO` for transferring form and view data
- `templates` for Thymeleaf UI pages
- `static` for CSS and JavaScript assets

## Main User Flow

1. A user registers with basic account details.
2. The user logs in and starts a session.
3. The user accesses the dashboard to create, edit, or delete blog posts.
4. Visitors can open any post from the home page.
5. Visitors can submit comments on individual blog posts.

## Configuration

Update the database and mail settings in `src/main/resources/application.properties` before running the project:

- MySQL database URL
- MySQL username and password
- SMTP email configuration if needed

Make sure a MySQL database named `blog` exists, or update the datasource URL to match your environment.

## How To Run

1. Clone the repository.
2. Open the project in IntelliJ IDEA, Eclipse, or any Java IDE.
3. Configure MySQL in `application.properties`.
4. Create the target database in MySQL.
5. Run the application using:

```bash
./mvnw spring-boot:run
```

On Windows, you can use:

```bash
mvnw.cmd spring-boot:run
```

6. Open the application in your browser:

```text
http://localhost:8080/
```

## Purpose

This project is suitable for learning and demonstrating:

- Spring Boot CRUD operations
- MVC architecture
- Thymeleaf-based server-side rendering
- Session handling
- JPA entity relationships
- Building a simple end-to-end full-stack Java web application

## Future Improvements

- Password encryption with Spring Security
- User role management
- Rich text editor for blog content
- Post categories and tags
- Search and pagination
- Profile management
- Email verification and password reset

## Author

Developed as a Spring Boot practice project for building a complete blog application with user and post management.

CINEMA TICKET BOOKING SYSTEM

Overview

This project is a Cinema Ticket Booking System, which allows users to book tickets for available movies in different cinema halls. 
The system includes entities for users, movies, cinema halls, show times, seats, and tickets, ensuring a structured and scalable approach to managing cinema reservations.

Features

User Management: Registration, login, and user role management.

Movie Management: Add, update, and list available movies.

ShowTime Management: Schedule movie screenings in different halls.

Cinema Hall Management: Manage halls and their capacities.

Seat Reservation: Select seats while booking tickets.

Ticket Booking: Purchase tickets and manage reservations.

Payment System: Integration with different payment methods.

Technologies Used
- Java 11, Gradle
    - Spring Boot -> Web, AOP, Security(JWT), Data JPA, Test, ArchTest, Validation
    - Spring Cloud Gateway
    - OpenFeign (for exchange rates service from `https://api.exchangerate.host/latest`
    - Redis
    - Postgres db, h2 db (only for testing)
    - Liquibase as db migration tool
    - DockerFile, Docker Compose
    - Project Lombok
    - MapStruct
    - Spring Fox (Swagger)
    - Slf4j
    - Sonarqube
    - Checkstyle

Installation Guide

Clone the Repository

git clone https://github.com/your-repo/cinema-ticket-booking.git
cd cinema-ticket-booking

Configure Database

Update application.properties with your database credentials:

spring.datasource.url=jdbc:mysql://localhost:3306/cinema_db
spring.datasource.username=root
spring.datasource.password=yourpassword

Build and Run the Application

mvn clean install
mvn spring-boot:run

API Documentation

Open Swagger UI: http://localhost:8080/swagger-ui.html

API Endpoints

Endpoint

Method

Description

/api/users/register

POST

Register a new user

/api/movies

GET

List all movies

/api/showtimes

GET

List all available showtimes

/api/tickets/book

POST

Book a ticket

Database Schema

User (id, name, email, password, role, etc.)

Movie (id, name, description, genre, duration, releaseDate, etc.)

CinemaHall (id, name, capacity, etc.)

ShowTime (id, startTime, movie_id, cinemaHall_id, etc.)

Seat (id, row, seatNumber, type, status, cinemaHall_id)

Ticket (id, ticketNumber, price, showTime_id, seat_id, user_id)

Future Enhancements

Online Payment Gateway Integration

Mobile App Support

Email/SMS Notifications for Ticket Confirmation

Admin Dashboard for Better Management

Contributors

[Your Name] - Lead Developer

[Contributor Name] - Backend Developer

[Contributor Name] - Database Engineer

License

This project is licensed under the MIT License.





Used:
    - Java 17, Gradle
    - Spring Boot -> Web, AOP, Data JPA, Test, Validation
    - Postgres db
    - Liquibase as db migration tool
    - DockerFile, Docker Compose
    - Project Lombok
    - MapStruct
    - Spring Fox (Swagger)
    - Slf4j

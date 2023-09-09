# Cinema Service Application

## Welcome to the Cinema Service Application

Welcome to the Cinema Service Application, a sophisticated Java-based Spring application meticulously crafted to provide a comprehensive and seamless cinema management experience. This application leverages a robust set of cutting-edge technologies to bring you a feature-rich platform for all your cinema-related needs.

## Contents

- [About the Application](#about-the-application)
- [Technologies Utilized](#technologies-utilized)
- [Database Schema](#database-schema)
- [Key Features](#key-features)
  - [User Registration and Activation](#user-registration-and-activation)
  - [Secure Authorization with JWT Tokens](#secure-authorization-with-jwt-tokens)
  - [User Roles](#user-roles)

## About the Application

The Cinema Service Application is designed to streamline cinema operations and enhance the experience for both users and administrators. With a focus on security, ease of use, and efficiency, this application empowers cinema enthusiasts to explore movie sessions, make reservations, and purchase tickets hassle-free.

## Technologies Utilized

This project harnesses the power of industry-leading technologies to deliver a reliable and feature-packed solution:

- **Spring Framework:** Leveraging the Spring ecosystem, including Spring Boot for rapid development and Spring Security for robust access control.
- **Java:** The backbone of the application, ensuring reliability and cross-platform compatibility.
- **Hibernate:** Providing seamless database interaction and management.
- **Thymeleaf:** A modern server-side Java template engine for building dynamic web applications.
- **Docker:** Ensuring efficient and consistent deployment across diverse environments.

## Database Schema

Here is the database schema of the Cinema Service Application, illustrating the structure of data:

![Database Schema](https://github.com/volodymyr-shepel/movieApp/blob/master/movieWebAppDatabaseSchema.jpg)

### Entity Descriptions

1. **Movie**
   - Represents movies, including titles, descriptions, and duration.

2. **MovieSession**
   - Contains information about individual movie showings, including start times and movie hall details.

3. **MovieHall**
   - Represents cinema halls, including seating arrangements.

4. **Seat**
   - Represents physical seats in a movie hall.

5. **Seat Pricing**
   - Contains pricing information for seats in movie sessions.

6. **Booking**
   - Stores reservations and purchases made by users.

7. **Ticket**
   - Records ticket details, including the user who purchased it, the associated movie session, and seat information.

8. **AppUser**
   - Stores user information, including username, password, and email.

9. **Confirmation Token**
   - Used for email confirmation during the user registration process.

The schema provides a structured representation of the data entities within the Cinema Service Application, enabling efficient data management and retrieval.

## Key Features

### User Registration and Activation

- **User Registration:** Users can easily create accounts and seamlessly register for the service.
- **Account Activation:** Account activation is achieved through a streamlined email confirmation process, enhancing security and ensuring the legitimacy of user accounts.

### Secure Authorization with JWT Tokens

- **Robust Authorization:** Robust authorization system relies on JSON Web Tokens (JWT) to ensure secure access.
- **Role-based Access Control:** Role-based access control precisely defines user privileges, guaranteeing a safe and controlled environment.

### User Roles

1. **Unauthorized User**
   - **Explore Sessions:** Enables users to explore movie sessions, filtering by session date.

2. **User**
   - **Reservation:** Builds upon the functionality of an unauthorized user.
   - **Ticket Reservation:** Allows users to reserve tickets for selected movie sessions, providing the convenience of completing the purchase before the reservation expires.

3. **Admin**
   - **Comprehensive Control:** Empowers administrators with comprehensive control over the application.
   - **Management:** Admins can manage movies, movie halls, and pricing for each session, including seat pricing based on movie-hall combinations.
   - **Admin Privileges:** Additionally, administrators have the authority to grant admin privileges to other users, facilitating collaborative management.

## API Documentation

To explore and interact with API, you can use [Postman Collection](movieWebApp.postman_collection.json).

If you're using Postman, you can import the collection by following these steps:

1. Open Postman.
2. Click on "Import" in the top left corner.
3. Choose "Import from Link."
4. Paste the following link:https://raw.githubusercontent.com/volodymyr-shepel/movieApp/master/movieWebApp.postman_collection.json
5. Click "Import" to add the collection to your Postman workspace.

Feel free to explore and test API endpoints using Postman!

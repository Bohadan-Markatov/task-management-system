# Task management system
## Table of content
## Overview
The Task Management App is a backend application designed for team-based task management. Key features include:
* User registration with email confirmation
* Login via email and password or Google OAuth2
* Project creation and management
* Adding and removing team members
* Creating, updating, and deleting tasks
* Adding comments and attachments to tasks

Users can update task statuses, add comments, and upload attachments. Security is ensured through JWT tokens, adhering
to best practices in REST API design. The backend is built with Spring Boot 3 and Spring Security 6.
## Visual Overview
## Technologies Used
* Spring Boot 3
* Spring Security 6
* JWT Token Authentication
* Spring Data JPA
* JSR-303 and Spring Validation
* OpenAPI and Swagger UI Documentation
* Liquibase: Manages database migrations
* Docker
* Google API
* Dropbox API
## Class diagram
![](assets/images/Tables.png)
## Registration
To register, send a POST request with the following JSON body:
```
{
  "email": "youremail@gmail.com",
  "password": "yourpassword",
  "repeatPassword": "yourpassword",
  "firstname": "Your name",
  "lastname": "Your lastname"
}
```
Afterward, check your email for a confirmation message from taskmanagement.springapp@gmail.com and click the confirmation button. If the token is valid and not expired (it expires in 60 minutes), your account will be enabled. If the confirmation token expired, you will be sent a new confirmation email. You can then log in using your email and password. You will receive a JWT token in the response, which you can use to access secured endpoints.

The token expiry time is currently set to 60 minutes. You can change this value in the EmailVerificationToken class in the method calculateExpiryDate() located at src/main/java/bohdan/markatov/org/model/EmailVerificationToken:
```
private Date calculateExpiryDate() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Timestamp(calendar.getTime().getTime()));
    calendar.add(Calendar.MINUTE, 60);
    return new Date(calendar.getTime().getTime());
}
```
## OAuth2
## Dropbox
## Installation
## Contacts

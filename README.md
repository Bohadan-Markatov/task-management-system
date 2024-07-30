# Task management system
## Table of content
1. [Overview](#overview)
2. [Features](#features)
3. [Technologies Used](#technologies-used)
    * [Backend](#backend)
    * [Frontend](#frontend)
4. [Visual Overview](#visual-overview)
5. [Installation](#installation)
6. [Contacts](#contacts)
## Overview
Task Management System is a full-stack application that enables users to manage their tasks while working in a team effectively.
It offers features such as user registration with secure email validation, project management (including project creation,
user addition and removal, task creation for team members, and project deletion), and task management (including task creation,
status updates, comments, file attachments, and task deletion). The application ensures security using JWT tokens 
and adheres to best practices in REST API design. The backend is built with Spring Boot 3 and Spring Security 6,
while the frontend is developed using Angular with Bootstrap for styling.
### Project architecture diagram
![](images/DIAGRAM.png)
## Features
* User Registration: Users can register for a new account.
* Email Validation: Accounts are activated using secure email validation token.
* User Authentication: Existing users can log in to their accounts using their email and password or via Google OAuth2.
* Project Management: Users can create projects, add and delete other users in the team, create tasks for team members, and retrieve all tasks by project. Team members can quit a project.
* Task Management: The project manager and responsible users can update the status of tasks, add comments, attach files, and delete tasks.
## Technologies Used
### Backend
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
### Frontend
* Angular
* Component-Based Architecture
* Lazy Loading
* Authentication Guard
* OpenAPI Generator for Angular
* Bootstrap
## Visual Overview
[![Watch on Loom](https://img.shields.io/badge/Watch%20on-Loom-00a4d9)](https://www.loom.com/share/)
## Installation
### With Docker
1. Download this project.
2. Create a new file named `.env` in the root directory of this project.
3. Copy all data from this [file](https://drive.google.com/file/d/1BMgBb2hqjRVglaZo6E9Ob4sNM-SuGKB8/view?usp=sharing) and paste it into the new `.env` file.
4. Open a terminal, navigate to the root of the project, and run the following commands:
```
# This command will compile the project
mvn clean package
# This command will generate two images: one for the backend and one for the database, and run them
docker-compose up --build
```
5. Once the backend and database are up and running, open a new terminal window, navigate to the root of the project, then to the folder named `frontend`, and run the following command:
```
# This command will generate an image for the frontend and run it
docker-compose up --build
```
6. Once the frontend container is launched, the application will be available in your browser on port 80.
7. To start using the application, paste the following URL into the browser search bar:
```
http://localhost:80/login
```
### Other ways
#### With locally installed database
If you have MySQL or Postgres installed on your machine, you can run the app with them. Just fill all database-related fields in the `application.properties` file (if using Postgres, you should also change the driver in the `application.properties` file, the dependency for Postgres is already added to the project). If you are going to run the backend in Docker, delete all database-related data from the `Dockerfile` and `docker-compose.yml` file.
#### With H2 in-memory database
All you need to do is delete rows from 3 to 9 (inclusive) in the `application.properties` file, copy rows from 1 to 5 (inclusive) from the `application.properties` file in the `test` folder, and paste them into the `application.properties` file in the `main` folder. Also, if you are going to run the backend in Docker, delete all database-related data from the `Dockerfile` and `docker-compose.yml` file.
#### Run backend locally
Fill in all rows with empty values in the `application.properties` file using data from this [file](https://drive.google.com/file/d/1BMgBb2hqjRVglaZo6E9Ob4sNM-SuGKB8/view?usp=sharing). If you are going to run the database locally too, change the database URL, username, and password to your own, and the driver if needed. If you are going to run the database in Docker, delete all backend-related data from the `Dockerfile` and `docker-compose.yml` file.
#### Run frontend locally
If you have Node.js installed, open your terminal, navigate to the root of the project, then to the `frontend` folder, and run the following command:
```
ng serve --port 80
```
## Contacts
### +380 97 293 50 98
### markatovbohdan@gmail.com
### [LinkedIn](https://www.linkedin.com/in/bohdan-markatov)
### [Telegram](https://t.me/BogdanMarkatov)
# Task management system
## Table of content
1. [Overview](#overview)
2. [Features](#features)
3. [Technologies Used](#technologies-used)
    * [Backend](#backend)
    * [Frontend](#frontend)
4. [Installation](#installation)
5. [Contacts](#contacts)
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
### Users can create a new account with email verification and log in using their email and password.
![](images/RL.gif)
### Both new and existing users can log in using their Google accounts via OAuth2 authentication.
![](images/OAuth2.gif)
### Project Management: Users can create projects, add and delete other users in the team, create tasks for team members, and retrieve all tasks by project. Team members can quit a project.
![](images/PM.gif)
### Task Management: The project manager and responsible users can update the status of tasks, add comments, attach files, and delete tasks.
![](images/TM.gif)
## Technologies Used
### Backend
![Static Badge](https://img.shields.io/badge/Spring%20Boot%203-brightgreen?style=plastic&logo=spring&logoColor=white)
![Static Badge](https://img.shields.io/badge/Spring%20Security%206-blue?style=plastic&logo=springsecurity&logoColor=white)
![Static Badge](https://img.shields.io/badge/JSON%20Web%20Token%20Authentication-red?style=plastic&logo=jsonwebtokens&logoColor=white)
![Static Badge](https://img.shields.io/badge/MySQL%20%26%20Spring%20Data%20JPA-yellow?style=plastic&logo=mysql&logoColor=white)
![Static Badge](https://img.shields.io/badge/WebSocket%20API-silver?style=plastic&logo=socket&logoColor=black)
![Static Badge](https://img.shields.io/badge/Swagger%20%26%20OpenAPI-green?style=plastic&logo=swagger&logoColor=white)
![Static Badge](https://img.shields.io/badge/Liquidbase-blue?style=plastic&logo=liquibase&logoColor=white)
![Static Badge](https://img.shields.io/badge/Docker-darkblue?style=plastic&logo=docker&logoColor=white)
![Static Badge](https://img.shields.io/badge/OAuth%202.0-gold?style=plastic&logo=webauthn&logoColor=black)
![Static Badge](https://img.shields.io/badge/Gmail%20API-blueviolet?style=plastic&logo=gmail&logoColor=white)
![Static Badge](https://img.shields.io/badge/Dropbox%20API-%230061FF?style=plastic&logo=dropbox&logoColor=white)
### Frontend
![Static Badge](https://img.shields.io/badge/Angular-red?style=plastic&logo=angular&logoColor=white)
![Static Badge](https://img.shields.io/badge/Bootstrap-%2305054B?style=plastic&logo=bootstrap&logoColor=white)
![Static Badge](https://img.shields.io/badge/NGINX-%23009639?style=plastic&logo=nginx&logoColor=white)
![Static Badge](https://img.shields.io/badge/Font%20Awesome-%23538DD7?style=plastic&logo=fontawesome&logoColor=white)

## Installation
### With Docker
1. Download this project.
2. Create a new file named `.env` in the root directory of this project.
3. Copy all data from this [file](https://drive.google.com/file/d/1BMgBb2hqjRVglaZo6E9Ob4sNM-SuGKB8/view?usp=sharing) and paste it into the new `.env` file.
4. Open a terminal, navigate to the root of the project, and run the following commands:
```
mvn clean package
docker-compose up --build
```
5. To start using the application, paste the following URL into the browser search bar:
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

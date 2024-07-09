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
To register, send a POST request to http://localhost:8080/api/auth/registration with the following JSON body:
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
You can also log in to the app using OAuth2 authentication by Google. Simply paste this URL into your browser where you have a Google account: http://localhost:8080/api/auth/oauth2-login. You will be redirected to Google's service where you should confirm that you want to log in to this app using your Google account.

In the service layer of the application, it will receive a JSON response from Google with your credentials and check if an account with that email already exists in the database. If it does, a new account will not be created; instead, a JWT token for the existing account will be returned. If the account does not exist, a new account will be created using the credentials from the JSON response from Google, and a JWT token for the new account will be returned. The password for the new account will be set to null, but this is secure because validation is used on the login endpoint, ensuring that nobody can use null as a value for the password field. 
## Dropbox
In this app, you can easily attach a file to your task. Simply send a POST request to http://localhost:8080/api/attachments/task/{taskId}, where {taskId} is the ID of an existing task assigned to you or a task in a project you manage. In the request body, include the path to an existing file on your machine that you want to attach to the task (you can use a file with any extension). The JSON should look like this: 
```
{
  "filePath": "C:\\Users\\user\\folder\\folder\\file.txt"
}
```
Also, use the JWT token you received upon login or through the OAuth2 endpoint (Auth Type - Bearer Token). After sending this request, your file will be saved in Dropbox.

In real web applications, files should be uploaded on the frontend and sent to the backend, as well as OAuth2 authentication where user credentials from Google are sent to the backend. However, since the frontend of this application is still in development, this functionality is handled on the backend.

You can get all attachments by task ID by sending a GET request to http://localhost:8080/api/attachments/task/{taskId}, using your JWT token. The response will be a list of AttachmentResponseDto objects that look like this:
```
{
  "id": 1,
  "taskId": 1,
  "taskName": "Task",
  "fileName": "example.txt",
  "filePublicLink": "http://example.com/file/example.txt",
  "uploadDate": "2024-07-09T12:34:56.789"
}
```
If you follow the filePublicLink in your browser, the file you uploaded to Dropbox will automatically be downloaded.

Also, if you look at the source code of DropboxService, you'll notice that the methods are nested and use recursion. It looks like this:
```
public String uploadFile(String filePath, Long taskId) {
    return uploadFile(filePath, taskId, 0);
}

private String uploadFile(String filePath, Long taskId, int attempt) {
    try (InputStream in = new FileInputStream(filePath)) {
        // implementation here...
    } catch (InvalidAccessTokenException e) {
        if (attempt > MAX_ATTEMPTS) {
            throw new RuntimeException("Can't upload file");
        }
        client = refreshClient();
        return uploadFile(filePath, taskId, attempt + 1);
    } catch (Exception e) {
        throw new RuntimeException("Can't upload file");
    }
}
```
This is necessary because the Dropbox API no longer supports long-lived access tokens. You can only get a short-lived access token that lasts 4 hours. As a result, the code catches InvalidAccessTokenException, attempts to refresh the DropboxClient which contains the access token, and then calls the method again.
## Installation
You can install and run this application in different ways. Here are three methods.
### Doker
1. Create a new file named .env in the root directory of this project. Copy all data from this file and paste it into the new .env file.
2. Open a terminal, navigate to the root of the project, and run the following commands:
```
mvn clean package
docker-compose up --build
```
The first command will compile the project, and the second will generate two images: one for the application and one for the database.

WARNING: If you run this app more than once with Docker, please drop all tables in the database before running the app again. Otherwise, you may encounter issues where Hibernate drops and creates tables by itself, and Liquibase scripts, which also insert roles, may not work properly.
### Locally Installed Database
By default, the application.properties file is configured for a MySQL database. If you are using PostgreSQL, you only need to change the driver (dependencies for PostgreSQL are already added to the project). Follow these steps:
1. Fill in all empty fields related to the database in the application.properties file.
2. Fill in the remaining empty fields using data from this file.
3. Run the app.
### H2 Database
1. Delete all fields in application.properties marked for deletion when using the H2 database.
2. Uncomment all fields marked for uncommenting when using the H2 database.
3. Fill in the remaining empty fields using data from this file.
4. Run the app.
## Contacts

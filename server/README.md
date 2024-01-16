# RentX - Server

## Debug and Execution

Use Eclipse or IntelliJ IDE to run the rentX api. (IntelliJ Preferred since we have access to ultimate edition for free and it is lite weight compared to Eclipse)
* Step 1: Run the user-directory sql script in our project's remote MySql server
* Step 2: Open the server/com.rentx.api directory in IntelliJ 
* Step 3: Edit the server/com.rentx.api/src/main/resources/application.properties with appropriate mysql connection string
* Step 4: Run the API
* Step 5: Listen to the api from [port:8080](http://localhost:8080/api/users)

***
## Git Ignore File
.gitignore file will contain those files that we don’t want to add to the GitHub repository such as .env or sensitive file.

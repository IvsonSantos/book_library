
# **Reference Documentation**

For reference, please follow the next steps:

- Pull the project
- Import the project as Maven project
- Open the Terminal and go inside the project and execute the following command:

`mvn package`

- Then, to run the project, execute the following command

`mvn docker:start`

This command will show you the starter log with the Starter CONTAINER ID
- To view the log trace, copy the CONTAINER ID and execute the following command:

`docker logs --follow {CONTAINER_ID}`

If everything is fine, open the Swagger API documentation of this project located in [http://localhost:8090/swagger-ui.html](http://localhost:8090/swagger-ui.html)

- To stop the project, use the next command:
`mvn docker:stop`

# **Structure of the API**

The project has 3 main controllers:

- The BookController that manages the books and report to it
- The BookFamilyController that manages the book families and report to it
- The FtpController that manages the upload of the geports generated


# **Endpoints on POSTMAN**

- **/api/bookFamilies - **GET** all book families
- **/api/bookFamilies/{id} - **PUT** to update the data of the book family with the JSON example:
  {
    "name": "test update"
  }
  

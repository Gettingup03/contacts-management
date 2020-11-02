# Contacts-Management WEB API

This WEB API allows you to manage contacts in a company.

After the API running, you can get the description thanks to the Swagger Documentation : http://localhost:8080/swagger-ui.html

A sample of Postman requests is present into the project.

### Tech

Contacts-Management WEB API uses the following libs :

* [Spring Boot] 
* [Hibernate]

### Installation

This API requires Java 8 or higher version.

#### Building for source

If you have make (make is a GNU command) on your local machine, you can build directly by the different target present in the Makefile into the project:

```sh
cd contacts-management
make build
```

If you want to build the project and get a docker image, run the following make target :

```sh
cd contacts-management
make buil-docker
```

### Docker

If you have built a docker image of the project, you can run the project with the following command :

```sh
docker run -p 8080:8080 app/contacts-management:latest
```
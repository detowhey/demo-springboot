# Order REST service

### Functionalities:

- Order
- User
- Customer
- Products
- Categories (products)
- Security (authentication and authorization)


### Technologies
<br>

- <img src="https://logospng.org/wp-content/uploads/java.png" width="30" height="30" alt="Java logo">  [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

- <img src="https://pbs.twimg.com/profile_images/1235868806079057921/fTL08u_H_400x400.png" width="30" height="30" alt="Spring Boot logo"> [Spring boot ecosystem](https://spring.io/projects/spring-boot/)  
  
- <img src="https://cdn.icon-icons.com/icons2/2415/PNG/512/postgresql_original_wordmark_logo_icon_146392.png"  width="30" height="30" alt="PostgreSQL logo"> [PostgreSQL](https://www.postgresql.org/)

- <img src="https://cdn.worldvectorlogo.com/logos/jwt-3.svg"  width="30" height="30" alt="JWT/JSON Web token logo"> [JWT (JSON Web Token)](https://jwt.io/)

- <img src="https://seeklogo.com/images/S/swagger-logo-A49F73BAF4-seeklogo.com.png"  width="30" height="30" alt="Swagger logo"> [Swagger](https://swagger.io/)

- <img src="https://i.pinimg.com/originals/5c/bb/a7/5cbba74b40ec0c0ce77b3db3ec1a5e05.png" width="30" height="30" alt="Docker logo"> [Docker/Docker Compose](https://www.docker.com/)

## Important

For the application to run you need to have [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) installed.

### Running the application
##### Build
```
docker-compose build
```
##### Application up
```
docker-compose up
```

### Base URL
http://localhost:8080/fighter

### [pgAdmin (PostgreSQL tool)](https://www.pgadmin.org/)

To manage the database, the **pgAdmin** tool was used.

The service will be available at the URL: **localhost:15433**

#### Default user:

- user: admin
- password: admin


### Documentation with Swagger
http://localhost:8080/swagger-ui/index.html
webshop
=======

### E-commerce application's backend.

<p>
  <img
    src="images/logo.jpg"
    alt="webshop logo"
    title="webshop logo"
    width="306"
    height="306"
  />
</p>

Prerequisites
-------------

To avoid any unexpected application behavior, make sure you have installed the following tools with the proper version
numbers:

- [Eclipse Temurin JDK 21](https://adoptium.net/temurin/releases/?version=21)
- [Maven 3.9.6](https://maven.apache.org/download.cgi)

Run project locally
-------------------

Be sure to copy `.env.example` to `.env` and update it with your local database connection parameters before running the
application.

### Build application and database schema populating it with test data with Flyway Maven plugin

```bash
mvn clean install
```

### Starting application with Spring Boot Maven plugin

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

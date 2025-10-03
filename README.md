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

[![Continuous integration](https://github.com/jbence1994-webshop/webshop-api/actions/workflows/build.yaml/badge.svg)](https://github.com/jbence1994-webshop/webshop-api/actions/workflows/build.yaml)

Prerequisites
-------------

To avoid any unexpected application behavior, make sure you have installed the following tools with the proper version
numbers:

- [Eclipse Temurin JDK 21](https://adoptium.net/temurin/releases/?version=21)
- [Maven 3.9.6](https://maven.apache.org/download.cgi)

Run project locally
-------------------

Be sure:

- to copy `.env.example` to `.env` and update it with your local database connection parameters before running the
  application.

- to copy `system_prompt.txt.example` to `system_prompt.txt` and update it with your local database connection
  parameters before running the application.

### Build application and database schema populating it with test data with Flyway Maven plugin

```bash
mvn clean install
```

### Starting application with Spring Boot Maven plugin

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

- To try application endpoints, open http://localhost:8080/swagger-ui/index.html in your web browser.

- To view api documentation, open http://localhost:8080/v3/api-docs in your web browser.

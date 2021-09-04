# Liv2Train
A Backend service for Training Centers.

## APIs

* <i> GET </i> : /trainingCenters - Get All the Training Centers. Filters can be applied on basis of centerCode, CenterName, city, state, pinCode, or any course offered by the center
* <i> POST </i> : /trainingCenters - Add a new training center. Proper Validation is handled using annotations, along with a customized error response on validation failure.

## Requirements for running the project locally

Following properties must be present in application.yml file for database configuration

* spring.datasource.url
* spring.datasource.username
* spring.datasource.password

## How to run locally

Build the maven project:

    mvn clean package
    
Run the Spring Boot Application:

    mvn spring-boot:run

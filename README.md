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
    
## How to run locally in a docker container

    docker run -d -p 9000:9000 --name Liv2Train karthikeysaxena/liv2train:latest

## Deployment

The backend service is also deployed on Heroku at: https://liv2train.herokuapp.com/

To get the list of all training centers, visit https://liv2train.herokuapp.com/trainingCenters


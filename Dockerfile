FROM openjdk:8
EXPOSE 9000
ADD target/Liv2Train-0.0.1-SNAPSHOT.jar Liv2Train-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/Liv2Train-0.0.1-SNAPSHOT.jar"]

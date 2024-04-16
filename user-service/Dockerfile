FROM openjdk:19-jdk

WORKDIR /app

COPY ./target/user-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]




#docker run --name postgres -e POSTGRES_PASSWORD=123 -e POSTGRES_USER=postgres -e POSTGRES_DB=users -p 5438:5438 -d postgres
#docker run --name pgadmin-container -p 5050:80 -e PGADMIN_DEFAULT_EMAIL=yassine@gmail.com -e PGADMIN_DEFAULT_PASSWORD=123 -d dpage/pgadmin4
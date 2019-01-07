FROM openjdk:8-jre

COPY target/*.jar /app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar", "--spring.datasource.url=jdbc:mysql://db:3306/SchoolOrganisation"]

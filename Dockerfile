FROM openjdk:21
EXPOSE 4500
ADD target/primary-health-care-1.jar primary-health-care-1.jar
ENTRYPOINT ["java", "-jar" ,"/primary-health-care-1.jar"]
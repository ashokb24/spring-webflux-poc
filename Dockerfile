FROM openjdk:11
COPY target/spring-webflux-poc-*.jar webfluxpoc-1.0.0.jar
ENTRYPOINT ["java","-jar","/webfluxpoc-1.0.0.jar"]
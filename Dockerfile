FROM eclipse-temurin:latest

WORKDIR /app


 
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# RUN addgroup -ms /bin/bash mvn
# RUN useradd -ms /bin/bash mvn
# RUN chown -R mvn:mvn /app 

RUN  bash ./mvnw dependency:go-offline
 
COPY src ./src


CMD ["bash","./mvnw", "spring-boot:run"]
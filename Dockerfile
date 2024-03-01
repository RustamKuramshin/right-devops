FROM gradle:7.4.0-jdk17 as build
WORKDIR /app
COPY --chown=gradle:gradle . /app
RUN gradle clean build --no-daemon

FROM openjdk:17.0.2-slim as builder
WORKDIR /app
COPY --from=build /app/build/libs/right-devops-1.0.0.jar /app/right-devops.jar
RUN java -Djarmode=layertools -jar right-devops.jar extract

FROM openjdk:17.0.2-slim
WORKDIR /app
COPY --from=builder app/dependencies/ ./
COPY --from=builder app/spring-boot-loader/ ./
COPY --from=builder app/snapshot-dependencies/ ./
COPY --from=builder app/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]

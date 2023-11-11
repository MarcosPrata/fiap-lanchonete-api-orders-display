FROM gradle:7.6.1-jdk17 AS build
COPY --chown=gradle:gradle . /home
WORKDIR /home/
RUN gradle build --no-daemon

FROM gradle:7.6.1-jdk17
COPY --from=build /home/application/build/libs/*.jar /app/
EXPOSE 80
WORKDIR /app
ENTRYPOINT ["java", "-jar", "application-1.0.jar"]

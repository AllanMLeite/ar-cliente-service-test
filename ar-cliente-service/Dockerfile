FROM alpine/git as clone

ENV JAVA_OPTS -Xms512m -Xmx512m -Djava.security.egd=file:/dev/./urandom -Duser.timezone=America/Sao_Paulo
WORKDIR /app
RUN git clone --verbose https://github.com/AllanMLeite/ar-cliente-service.git /app

FROM gradle:4.9.0-jdk8-alpine as build
USER root
COPY --from=clone /app/ /app
WORKDIR /app
EXPOSE 8080

RUN gradle build
ENTRYPOINT java -jar build/libs/cliente-service-0.0.1-SNAPSHOT.jar
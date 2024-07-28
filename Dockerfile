FROM openjdk:21-jdk-slim as builder
WORKDIR application
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM openjdk:21-jdk-slim
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
EXPOSE 8080

#FROM node:lts-alpine AS build
#WORKDIR usr/src/app
#COPY frontend/package*.json ./
#RUN npm install -g @angular/cli
#RUN npm install --force
#COPY frontend ./
#RUN npm run build --prod
#
#FROM nginxinc/nginx-unprivileged
#COPY ./nginx.conf /etc/nginx/conf.d/default.conf
#COPY --from=build /usr/src/app/dist/frontend/browser /usr/share/nginx/html
#CMD ["nginx", "-g", "daemon off;"]
#EXPOSE 80

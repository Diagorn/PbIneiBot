#build
FROM anonymoussquad/bitbucket-jdk-11-slim AS build
MAINTAINER Diagorn
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#package and run
FROM openjdk:11-jre-slim
MAINTAINER Diagorn
COPY --from=build /home/app/target /usr/local/lib
ENTRYPOINT ["java","-jar","/usr/local/lib/PbIneiBot-1.0-SNAPSHOT.jar","BOT_TOKEN"]
#bot token not inserted because of security issues + discord automatically changes it
#if it is shown on GitHub
FROM gradle:6.7-jdk11 as builder

COPY src /app/src
COPY *.gradle /app/
WORKDIR /app
#RUN gradle wrapper --no-daemon
RUN gradle build --no-daemon
COPY app/bin /app/bin

FROM openjdk:11-jdk
#MAINTAINER Eric Goebelbecker "eric@ericgoebelbecker.com"
ENV JAVA_VERSION 8u31
ENV PACKAGE_ROOT /app
#ADD app /app/
COPY --from=builder /app/bin /app/bin
COPY --from=builder /app/build/libs/jaeger-tutorial*.jar /app/libs/jaeger-tutorial.jar
CMD ["/app/bin/start.sh"]
#CMD []

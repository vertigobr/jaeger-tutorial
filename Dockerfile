#
# Caches most dependencies
# Reduces build time even when pom.xml changes
# From time to time you'll want to overwrite pom.xml.cache again
#
FROM maven:3-jdk-11 as cache
WORKDIR /app
COPY pom.xml.cache /app/pom.xml
RUN mvn dependency:go-offline

#
# Builder stage reuses cached repo from previous stage
#
FROM maven:3-jdk-11 as builder
COPY --from=cache /root/.m2/repository /root/.m2/repository
WORKDIR /app
COPY pom.xml /app/
RUN mvn dependency:go-offline
COPY src /app/src
RUN mvn package
COPY app/bin /app/bin

#
# Final image reuses 'repository' layer when possible
#
FROM openjdk:11-jdk
ENV PACKAGE_ROOT /app
COPY --from=builder /app/target/thin/root/repository /app/libs/repository
COPY --from=builder /app/bin /app/bin
COPY --from=builder /app/target/thin/root/jaeger-tutorial*.jar /app/libs/jaeger-tutorial.jar
CMD ["/app/bin/start.sh"]

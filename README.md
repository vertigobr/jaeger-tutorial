# Jaeger Tutorial

Spring Boot project with Jaeger tracing enabled.

## Starting app: Okteto Stack + Jaeger Helm Chart

Deploy Jaeger using its Helm Chart:

```
helm repo add jaegertracing https://jaegertracing.github.io/helm-charts
helm upgrade -i jaeger -f values-jaeger.yaml jaegertracing/jaeger
```

Deploy jaeger-tutorial:

```
okteto stack deploy --build
```

## Development with Okteto

In a terminal terminal user Okteto to switche the app container:

```
okteto up
mvn package
mvn spring-boot:run
```


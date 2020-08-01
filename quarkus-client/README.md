# Quarkus Client example

This project uses Quarkus Command mode and the SmallRye client.

## Build

```
mvn clean install
```

## Run

Make sure you have the server running, then:

```
java -jar target/quarkus-client-1.0.0-SNAPSHOT-runner.jar 1
```

Where `1` is the person id.

This will result in the person being fetched from the server:

```

```
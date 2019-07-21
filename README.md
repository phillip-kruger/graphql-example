# MicroProfile GraphQL Example

This is an example of the future MicroProfile GraphQL API.

The services are exposed with both REST and GraphQL for comparison.

## Run the example

At the moment this project depend on unpublished SNAPSHOT versions projects:

* The MicroProfile GraphQL API : https://github.com/eclipse/microprofile-graphql
* The SmallRye Implementation : https://github.com/phillip-kruger/smallrye-graphql
* GraphQL SPQR (microprofile-proto branch): https://github.com/phillip-kruger/graphql-spqr/tree/microprofile-proto

Once you have build the above and have the SNAPSHOT versions in your local repo, simply clone this repo and:

```
mvn clean install
```

This will start the application.

Go to http://localhost:8080 to test the application

To stop the application, `ctrl-c` in the maven session.
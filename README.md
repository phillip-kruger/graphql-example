# MicroProfile GraphQL Example

This is an example of the future MicroProfile GraphQL API. It's done as part of this presentation: http://bit.ly/mp-graphql-presentation

The services are exposed with both REST and GraphQL for comparison.

## Run the example

```
mvn clean install
```

This will start the application. (First start takes long, as it fetch and install unpublished SNAPSHOT dependencies)

Go to http://localhost:8080 to test the application

To stop the application, `ctrl-c` in the maven session.

### Dependency on SNAPSHOT versions

At the moment this project depend on unpublished SNAPSHOT versions of some projects. 
However, as part of the build these projects are downloaded and build to be installed in your local repository.

The projects that's being downloaded:

* The MicroProfile GraphQL API : https://github.com/eclipse/microprofile-graphql
* The SmallRye Implementation : https://github.com/phillip-kruger/smallrye-graphql
* GraphQL SPQR (microprofile-proto branch): https://github.com/phillip-kruger/graphql-spqr/tree/microprofile-proto

Once install, a `installed` file gets create in the module. To re-install a certain module, remove that file.

### Examples

#### Demo 1

```
{
  profileFull(personId:1) {
    person{
      surname
    }
  }
}
```

#### Demo 2

```
{
  profile(personId:1){
    person{
      surname
    }
    scores{
      name
      value
    }
  }
}
```

in the log file:

```
======= Getting person [1] =======
======= Getting scores [512-46-5065] =======
```

without score

```
{
  profile(personId:1){
    person{
      surname
    }
  }
}
```

in the log file:

```
======= Getting person [1] =======
```

#### Demo 3

```
{
  person(personId:1){
    surname
    scores{
      name
      value
    }
  }
}
```

or without score

```
{
  person(personId:1){
    surname
  }
}
```

#### Demo 4

```
{
  people{
     surname
  }
}
```

#### Demo 5

```
mutation UpdatePerson{
  updatePerson(person : {names: "Piet"}){
    id
    names
    surname
  }
}
```

and then update

```
mutation UpdatePerson{
  updatePerson(person : {id: 102, surname: "Pompies", names: "Piet"}){
    id
    names
    surname
  }
}
```
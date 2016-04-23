# tyche

Experimenting with `clojure-api` and (eventually) `component`

## Usage

### Run the application locally

`lein ring server`

### Run the tests

`lein test`

### Packaging and running as standalone jar

```
lein do clean, ring uberjar
java -jar target/server.jar
```

### Packaging as war

`lein ring uberwar`

## Who was Tyche?

The Greek goddess of chance, with whom the Roman Fortuna was later identified; a capricious dispenser of good and ill fortune. 

## License

Copyright ©  Michael Ruggiero

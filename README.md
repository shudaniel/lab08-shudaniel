<<<<<<< HEAD

# parsing-starter-01

This is the starter code for a lab on recursive descent parsing.

See https://ucsb-cs56-f17.github.io/lab/lab08/ for more explanation.

This code uses maven rather than ant.

| To do this ... | type this...  | Notes |
|----------------|---------------|-------|
| compile        | `mvn compile` |       |
| clean up       | `mvn clean`   |       |
| test           | `mvn test`    |       |
| build a jar    | `mvn package` | Builds a jar in `target/cs56Parser-1.0-SNAPSHOT.jar` |
| run            | see below     |       |

# To run

First do:

```
mvn package
java -jar target/cs56Parser-1.0-SNAPSHOT.jar
```

# Jacoco test coverage

To check test coverage:

```
mvn test
mvn jacoco:report
```

Then, in a web broswer, open up: 

```
target/site/jacoco/index.html
```

On Mac, you can just type the following to open the file in your default web browser:

```
open target/site/jacoco/index.html
```

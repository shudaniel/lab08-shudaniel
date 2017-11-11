<<<<<<< HEAD

# parsing-starter-01

This is the starter code for a lab on recursive descent parsing.

See https://ucsb-cs56-f17.github.io/lab/lab08/ for more explanation.

* [javadoc](http://ucsb-cs56-f17.github.io/parsing-starter-01/javadoc/)
* [test coverage](http://ucsb-cs56-f17.github.io/parsing-starter-01/jacoco/)

# Using Maven

This code uses maven rather than ant.

| To do this ... | type this...  | Notes |
|----------------|---------------|-------|
| compile        | `mvn compile` |       |
| clean up       | `mvn clean`   |       |
| test           | `mvn test`    |       |
| build an executable jar    | `mvn package` | Builds a jar in `target/cs56Parser-1.0-SNAPSHOT.jar` |
| generate javadoc    | `mvn javadoc:javadoc` | Javadoc goes into `docs/javadoc` |
| generate a test coverage report   | `mvn jacoco:report` | Report goes into `docs/jacoco` |	
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
docs/jacoco/index.html
```

On Mac, you can just type the following to open the file in your default web browser:

```
open docs/jacoco/index.html
```

Or push the site to github, and see:



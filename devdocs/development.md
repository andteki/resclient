# Developer Documentation

## Used software

The program was created in VSCode, supplemented with Java support, using the "No build tools" feature.

## Dependencies used

To operate:

* gson

Gson is required for conversion.

For testing:

* testng
* jcommander
* slf4-api

jcommander and slf4-api are required for testing.

## Classes

There are two classes:

* Client
* Convert

The Client class is a programming library for REST API servers.

Convert helps you convert JSON strings to Java objects and vice versa.

The Client class uses the Java 11 HttpClient class.

## Client class

The names of the methods of the methdos of the Client class correspond to the HTTP methods.

* get()
* post()
* put()
* delte()

In the case of th post(), put() and delete() methods, it is possible to specify an extra parameter, which is by definition optional. A token for identification can be passed in this parameter.

The HTTP header currently passed is "Authorization":"Bearer". There may be servers where a different text needs to be passed before the token instead of "Bearer".

## Convert class

The Convert class uses generic types so that the user can flexibly use any object.

* toListObject()
* toObject()
* listToJson()
* toJson()

## Creating Java documentation

Java documentation can be created by running the scripts/create_docs.sh script, which is a Bash script.

```bash
bash scripts/create_docs.sh
```

The result is placed in the docs directory, it appears on GitHub as a web page.

## Testing

### Automatic test

The Client class can be tested automatically using Typicode's mock REST API.

* test/auto

The automatic test is implemented with TestNG.

There is an api directory in the test directory, for mocking the REST API, with hai-server.

## Todo

* Implementation of identification for the get() method.
* The PATCH method must be implemented.
* More automatic tests are needed for the Client class.
* Writing automatic tests for the Convert class.
* Creating asynchronous methods.
* Setting the Authorization HTTP header with a constructor and method.

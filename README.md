# Generic Java Web Application

## TL;DR

The project provides a POM framework for a multi-module project where one child 
(web) depends on another (jpa). Additionally, it provides a parent POM file 
which has dependencies for testing, code analysis, and reporting.

## Configuration

This project uses `lombok` to handle automatically generating getter and
setter method. It does a few other things as well, but that's the main value.

### Eclipse

If you are using Eclipse, visit https://howtodoinjava.com/automation/lombok-eclipse-installation-examples/
in order to configure your IDE. Restart Eclipse.

### postgres

```
docker run --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=password -d postgres
```

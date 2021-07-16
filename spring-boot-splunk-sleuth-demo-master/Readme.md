# Spring Boot Sleuth Splunk demo  [![Build Status](https://travis-ci.com/barrycommins/spring-boot-splunk-sleuth-demo.svg?branch=master)](https://travis-ci.com/barrycommins/spring-boot-splunk-sleuth-demo)
Sample project to show log forwarding from a [Spring Boot](https://projects.spring.io/spring-boot/) application to [Splunk](https://www.splunk.com/) via a file forwarder.

[Sleuth](https://cloud.spring.io/spring-cloud-sleuth/) is also added to generate IDs for each request

## Build

Build the application as a [Docker](https://www.docker.com/) image using:

```bash
mvn clean package
```

## Run

Run the included docker-compose file with:

```bash
docker-compose up
```

This will bring up three containers: A demo application, Splunk, and a Spunk Forwarder.

Logs are forwarded to Splunk via a shared volume between the application and forwarder.

It is possible to send logs to Splunk via Docker's logging mechanism, but the goal here was to use the file forwarder to replicate a production setup.


## Generate logs

Application startup will generate some logs.

Calling the demo endpoint will generate some more logs, with Sleuth trace and Span Ids.

```bash
curl http://localhost:7070?name=test
```

## View Logs

Got to [Splunk login](http://localhost:8200) to view the logs.

Login is admin/password

## Stop

Stop the containers and remove unused volumes with:

```bash
docker-compose down -v
```

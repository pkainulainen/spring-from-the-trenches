This blog post is the example application of the blog post:

* [Spring from the Trenches: Configuration Properties Bean]()

Prerequisites
=============

You need to install the following tools if you want to run this application:

* [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Maven](http://maven.apache.org/) (the application is tested with Maven 3.2.1)

Running the Tests
=================

You can run the unit tests by using the following command:

    mvn clean test

Running the Application
=======================

You can run the application by using the following command:

    mvn clean jetty:run -P dev
    
You can get the runtime configuration of the application by sending a GET request to the url:

    http://localhost:8080/config
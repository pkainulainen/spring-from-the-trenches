This blog post is the example application of the blog post:

* [Spring From the Trenches: Creating PDF Documents With Wkhtmltopdf](http://www.petrikainulainen.net/programming/spring-framework/spring-from-the-trenches-creating-pdf-documents-with-wkhtmltopdf/)

*Note:* This example application (and the blog post) is based on the ideas of [Kyösti Herrala](https://www.linkedin.com/in/kherrala)

Prerequisites
=============

You need to install the following tools if you want to run this application:

* [wkhtmltopdf](http://wkhtmltopdf.org/)
* [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Maven](http://maven.apache.org/) (the application is tested with Maven 3.3.3)

Running the Tests
=================

You can run the unit tests by using the following command:

    mvn clean test

Running the Application
=======================

You can run the application by using the following command:

    mvn clean spring-boot:run

This is the example application of my blog posts:

* [Spring From the Trenches: Using Null Values in DbUnit Data Sets](http://www.petrikainulainen.net/programming/spring-framework/spring-from-the-trenches-using-null-values-in-dbunit-datasets/)
* [Spring From the Trenches: Resetting Autoincrement Columns Before Each Test Method](http://www.petrikainulainen.net/programming/spring-framework/spring-from-the-trenches-resetting-auto-increment-columns-before-each-test-method/)

If you don't know how you can write integration tests for Spring Data JPA repositories, 
You should read a blog post titled: [Spring Data JPA Tutorial: Integration Testing](http://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-integration-testing/).

Running the Tests
=================

If you want to run the unit tests, you should invoke the following command at command prompt:

        mvn clean test -P dev
        
If you want to run the integration tests, you should invoke the command at command prompt:
        
        mvn clean verify -P integration-test




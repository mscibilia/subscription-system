#SUBSCRIPTION SYSTEM ARCHITECTURE
 ________________               ______________________                 __________               _______________
|                |             |                      |   produces    |          |   consumes  |               |
| public service |------------>| subscription service |-------------->| RabbitMQ |<------------| email service |
|________________|             |______________________|               |__________|             |_______________|


#How to build and run the subscription system
Pre-requisite: Ensure Docker is installed on your machine

From root directory of the cloned repository run:
docker compose up --build

Note: The maven build runs as part of the Docker build for all API components.

#How to use the subsciption system
1. Import the postman collection (Subscription_System.postman_collection.json) located in the repository root directory.
2. Send requests

Alternatively, The public service api is accessable through port 5000 so requests can be made to http://localhost:5000/subscriptions via a method of your choosing.

The Swagger UI is accessible at http://localhost:5000/swagger-ui.html (when running in Docker container)

##Implementation Details

*When sending 'dateOfBirth' fields in request payloads, the allowed format is **dd/MM/yyyy**

*Valid values for the 'gender' field are MALE, FEMALE and OTHER

*You cannot create a subscription with an email address when there is an existing subscription with that email.

*When an email is 'sent', you can observe a log entry in the email-service in format of: "Sending email to <email-address> with message <message>" (no emails are actually sent)


#Frameworks/Libraries

*Spring Boot - Provides a fast way to create an opinionated Spring application with minimal configuration. 

*Spring AMQP - Provides abstractions for interacting (sending/receiving messages) with AMQP-based message brokers (i.e. RabbitMQ). Used this as a way to configure and create an exhange, queue and bindings in both the producer and consumer as well as providing a template to easily send messages to a RabbitMQ exchange with serialization.

*Spring Data JPA - Provides abstractions for JPA based data access layers. Used to perform database operations without having to write logic to build SQL queries.

*Spring Validation - Provides annotations to validate Java Beans. Used as a way to validate contraints on the fields of the request models and control the format the violations are returned to the caller.

*Spring Security - Provides authentication and authorization to applications. Used as an easy way to configure basic authentication on the subsciption service so that it only accepts requests from the public service system user.

*Spring Webflux - Provides a non-blocking HTTP client that supports Reactive Streams. Used because it is currently the recommended library to use to make HTTP calls in a Spring application.

*Lombok - Provides annotations that generate boilerplate code for Java classes at compile time. Used to reduce time spent writing boilerplate code, making classes (especially POJOs) more succinct.


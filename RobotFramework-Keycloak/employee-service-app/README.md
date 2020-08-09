keycloak-springboot-actuator : Monitoring keycloak protected application using SpringBoot Actuator
==================================================================================================

What is it?
-----------

The `keycloak-springboot-actuator` demonstrates how to extend Spring Boot Actuator 
to trace all REST calls to an application which is protected by Keycloak. 
Here we are extending httptrace endpoint to capture Request body, Response body 
and Login User.

To demonstrate tracing, a RESTful SpringBoot application is created and it is secured with <span>Keycloak</span>.

Endpoint exposed by this application

* `employee/{ID}` - To retrieve employee details and requires authentication
* `actuator/httptrace` - To retrieve in-memory traces collected by the spring boot actuator 


System Requirements
-------------------

All you need to build this project is 

*  Java 11 (Java SDK 1.11) or later 
*  Maven 3.3.8 or later.
*  Keycloak Server version 8.0.1
*  Springboot 2.2.2.RELEASE

Configuration in <span>Keycloak</span>
--------------------------------------

Prior to running this application you need to configure keycloak

*  Create realm `springboot-keycloak`
*  Create client `keycloak-springboot-actuator` under realm `springboot-keycloak` and set `Access Type` as `bearer-only`
*  Create realm level role 'user'
*  Create user `bob` and assign role 'user'

 Setup `public` for Access Token and use it to test our `bearer-only` app

 *  Create client `postman` under realm `springboot-keycloak` and set `Access Type` as `public`
 


Build and Run the springboot-user-attributes
--------------------------------------------

1. Open a terminal and navigate to the root directory of this `keycloak-springboot-actuator`.

2. Build application
   ```
    mvn clean install
    ```
2. Start keycloak-springboot-actuator application using below command

   ````
   mvn spring-boot:run

   ````


 Test using postman
 ------------------

 a) Get AccessToken using below url for user `bob`
    http://localhost:8080/auth/realms/springboot-keycloak/protocol/openid-connect/token
    
 b) Use the above AccessToken to request  'GET http://localhost:8081/employee/123'
 
 c) Now use 'http://localhost:8081/actuator/httptrace', to see http trace of all the previous REST calls to the application

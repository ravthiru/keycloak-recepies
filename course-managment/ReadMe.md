
Couse Management SpringBoot Application providing REST API , showcasing integration with Keycloak.  

Helps to understand autherization feature of Keycloak.  

### Realm : 
university

### client : 
course-management

### Roles 

1. teacher
2. ta
3. parent
4. student

### Authorization Scopes
 
 view  
 
 add  
 
 edit  
 
 delete  
 


### Users 


    role : teacher  
    username : alen.tuning

    role : student  
    username : rose.white


### How to run the application

	
	start application
	
	# mvn spring-boot:run
	
	
	Get Access Token before requesting Course REST API, 
	You can use Postman for getting access token  from following url
	
	 http://localhost:8080/auth/realms/university/protocol/openid-connect/token
	
	
	Get the course details  
	
	 http://localhost:8090/courses/1001  
	
	Delete Course   
	
	 DELETE http://localhost:8090/courses/1001  
	

### Debug
    #mvn spring-boot:run -Dspring-boot.run.jvmArguments=
    "-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"   
 
    


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


    role: ta  
    useraname : peter.jackson  
  
 
    role : student  
    username : rose.white

     anne wheeler

### Test
	
	Login scripts authenticates user using username and password and sets up    
	the .curlrc file so that you can use curl to access services which require a     
	bearer authorisation token.
	
	sh login.sh alen.tuning  
	
	Call the url to get the course  
	
	curl -v  http://localhost:8090/courses/1001
	
	
    

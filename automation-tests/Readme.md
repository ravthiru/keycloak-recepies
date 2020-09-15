

# Automation-Tests

This project is to demonstrate writing automation test cases for keycloak protected APIs

## How to stepup and run the automation test cases

### Step 1: Start Keycloak and Configure
 
 Go to Directory keycloak and then execute ```run-docker.sh```, it will start the Keycloak and Configure the Keycloak with needed Clients and Users
    
### Step 2: Start the app    
	
 Sample spring-boot Application used for this project can be found in ```employee-service-app```  
 To start the Application Run below command
	
	# mvn spring-boot:run
  
  
### Step 3.1:  Create Newman Docker image  
  
  Before Executng the Automation testcase create newman image with ```newman-reporter-htmlextra``` library
  You run the ```build-image.sh``` script present in  /test-framework/build. this will create name ```newman-docker/newman-htmlextra```

### Step 3.2: Run the Automation Tests
   
   Sample Postman Collection can be found in ```test-framework/tests/``` directory
   You can use ```run_tests.sh``` to run test cases placed in tests folder
  
  
### Test Report	
	
  Test report will be generated in  ```test-framework/tests/``` directory
  

 **More detailed explaination can be found on Medium article**
 https://medium.com/@ravthiru/automated-testing-of-oauth2-openid-protected-api-605f8a70351f
 
 
 

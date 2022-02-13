springboot-oauth-bearer: SpringBoot OAuth2 Resource Server with Keycloak as Provider
======================================================================================

Find detailed info on medium https://ravthiru.medium.com/springboot-oauth2-with-keycloak-as-provider-c31b2897e913

What is it?
-----------

The `springboot-oauth-bearer` demonstrates how to configure Springboot OAUth2 Resource Server
to use Keycloak as Provider. We write a Springboot application providing API which is protected 
with Keycloak. Only Requests with valid tokens can request API.  

System Requirements
-------------------

All you need to build this project is 

* Java 11 (Java SDK 1.11) or later 
*  Gradle
*  Keycloak Server version 16+

Build and Run the springboot-oauth-bearer
--------------------------------------------

1. Open a terminal and navigate to the root directory of this springboot-Keycloak.

2. Build application
   ```
    gradle build
    ```
2. Build and Start springboot-keycloak application using below command

   ````
   gradle bootRun

   ````

Test application
-----------------

To Get the Token

curl  POST 'http://localhost:8080/realms/springboot/protocol/openid-connect/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'client_id=demoapp' \
--data-urlencode 'username=testuser' \
--data-urlencode 'password=test123' \
--data-urlencode 'grant_type=password'

To Request The API

curl 'http://localhost:8081/employee/101' --header 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICIzYmI2R3ZMaW9fMk82cVZadHlJcTdmV1hQb0FKX0stSDU3bmgtNUNqbHBBIn0.eyJleHAiOjE2NDQ3NTUwMTAsImlhdCI6MTY0NDc1NDcxMCwianRpIjoiZmM2MWM5MmYtYTFiMC00MjhlLWFmMWEtNDM2ZjZjNmExZDk0IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3JlYWxtcy9zcHJpbmdib290IiwiYXVkIjoiYWNjb3VudCIsInN1YiI6ImJhZGUwMmU3LTE0YTYtNDJjMC1hMDA1LWI2MTBhZGRhZjljNiIsInR5cCI6IkJlYXJlciIsImF6cCI6ImRlbW9hcHAiLCJzZXNzaW9uX3N0YXRlIjoiMmE5NjA2OGEtMjA4Ny00YzAzLWFlZjgtYmY3NDMwNDNmZmQ4IiwiYWNyIjoiMSIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIiwiZGVmYXVsdC1yb2xlcy1zcHJpbmdib290Il19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJwcm9maWxlIGVtYWlsIiwic2lkIjoiMmE5NjA2OGEtMjA4Ny00YzAzLWFlZjgtYmY3NDMwNDNmZmQ4IiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJ0ZXN0dXNlciJ9.fh1tgCWtIlouoPgbNLvrBPBCq4Xa5_WOlRoIg16Bs_f9etSAY0qinQDeeNEblEfPRc_saTum7FcNcWUw078Nq_Hi_rfOuPHlqSVcM_i0imE7Acgf3yfEjmpW25dWl5IQNQDR2LtOftIjbc5w1wTCJO27ZlRjjyLO2aJ_BhnJvZXwITwcMigCWebMzY9wb0qusapJ6vh6FZWStFQ21vJUXwO-pbUuCmo3g7s33fiylGhBnQEF0U03QUXU84yCdqX5GjpOUPt80PzHUyUogPHwNGNKakEim8GnnnvwEQ44dMVx7uzSRtTJj6c-Nbt0HoBpS0z82rQamogeIXPxvzHEOQ' 

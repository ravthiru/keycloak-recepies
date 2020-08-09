*** Settings ***
Library    String
Library    REST		ssl_verify=false


*** Variables ***

${keycloak_url} 	https://localhost:8080/
${app_url}              https://localhost:8081/


*** Test Cases ***

Get User after getting token from keycloak
  Post         ${keycloak_url}/auth/realms/springboot-keycloak/protocol/openid-connect/token
  Output       response body session-token
  ${token}=    String	response body session-token 
  Output       ${token}
  @{chars} =	Split String To Characters	${token}
  Output	${chars}[0]	
  Output       ${app_url}
  GET          ${app_url}/employee/123	   headers={ "Access-Token" : "${chars}[0]" }
  Output

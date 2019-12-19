package com.lantana.keycloak.web;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.IDToken;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@RestController
public class UserServiceController {

    private @Autowired HttpServletRequest request;

	@GetMapping(value = "/userinfo", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserData handleUserInfoRequest(Principal principal) {
		System.out.println("principal "+principal);

		UserData user = new UserData();

		if (principal instanceof KeycloakPrincipal) {

			KeycloakPrincipal<KeycloakSecurityContext> kp = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
			AccessToken token = kp.getKeycloakSecurityContext().getToken();
			user.setId(token.getId());
			user.setUserName(token.getName());
			Map<String, Object> otherClaims = token.getOtherClaims();
			user.setCustomAttributes(otherClaims);
		}
		return user;
	}

	@GetMapping(value = "/public", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> handlePublicRequest() {
		return Collections.singletonMap("message", "public access");
	}
}

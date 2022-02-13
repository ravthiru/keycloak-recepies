package net.codeslate.keycloak.controller;


import net.codeslate.keycloak.model.Employee;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class EmployeeController {

    @GetMapping(value = "/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getEmployee(@AuthenticationPrincipal Jwt principal, @PathVariable String id) {
        System.out.println(principal.toString());
        System.out.println(principal.getClaimAsString("preferred_username"));
        return new Employee(id, "John Newman");
    }
}

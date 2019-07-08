package com.lantana.school.course.coursemanagment.security;


import java.util.List;

import org.keycloak.AuthorizationContext;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.idm.authorization.Permission;

public class Identity {

    private final KeycloakSecurityContext securityContext;

    public Identity(KeycloakSecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    /**
     * An example on how you can use the {@link org.keycloak.AuthorizationContext} to check for permissions granted by Keycloak for a particular user.
     *
     * @param name the name of the resource
     * @return true if user has was granted with a permission for the given resource. Otherwise, false.
     */
    public boolean hasResourcePermission(String name) { 
        return getAuthorizationContext().hasResourcePermission(name);
    }

    /**
     * An example on how you can use {@link KeycloakSecurityContext} to obtain information about user's identity.
     *
     * @return the user name
     */
    public String getName() {
        return securityContext.getIdToken().getPreferredUsername();
    }

    /**
     * An example on how you can use the {@link org.keycloak.AuthorizationContext} to obtain all permissions granted for a particular user.
     *
     * @return
     */
    public List<Permission> getPermissions() {
        return getAuthorizationContext().getPermissions();
    }

    /**
     * Returns a {@link AuthorizationContext} instance holding all permissions granted for an user. The instance is build based on
     * the permissions returned by Keycloak. For this particular application, we use the Entitlement API to obtain permissions for every single
     * resource on the server.
     *
     * @return
     */
    private AuthorizationContext getAuthorizationContext() {
        return securityContext.getAuthorizationContext();
    }
}

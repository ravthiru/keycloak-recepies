package com.lantana.keycloak.customtrace;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.keycloak.KeycloakSecurityContext;
import org.springframework.boot.actuate.autoconfigure.trace.http.HttpTraceProperties;
import org.springframework.boot.actuate.trace.http.Include;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@ConditionalOnProperty(value = "management.trace.http.enabled", matchIfMissing = true)
public class PrincipalTraceFilter extends OncePerRequestFilter {

    private TraceManager traceManager;

    private HttpTraceProperties traceProperties;        

    public PrincipalTraceFilter(TraceManager traceManager,
            HttpTraceProperties traceProperties) {
        super();
        this.traceManager = traceManager;
        this.traceProperties = traceProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);

        } finally {
            if (traceProperties.getInclude().contains(Include.PRINCIPAL)) {
            	KeycloakSecurityContext keycloakSecurityContext = (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
            	if(keycloakSecurityContext !=null)
            	traceManager.updatePrincipal(keycloakSecurityContext.getToken().getName());
            }
        }

    }
}
package com.lantana.keycloak.customtrace;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.web.trace.servlet.HttpTraceFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

@Configuration
@Order(1)
@ConditionalOnProperty(value = "management.trace.http.enabled", matchIfMissing = true)
public class ActuatorSecurityConfig extends WebSecurityConfigurerAdapter {

    private HttpTraceFilter httpTraceFilter;

    private ContentTraceFilter contentTraceFilter;

    private PrincipalTraceFilter principalTraceFilter;

    public ActuatorSecurityConfig(HttpTraceFilter httpTraceFilter,
            ContentTraceFilter contentTraceFilter,
            PrincipalTraceFilter principalTraceFilter) {
        super();
        this.httpTraceFilter = httpTraceFilter;
        this.contentTraceFilter = contentTraceFilter;
        this.principalTraceFilter = principalTraceFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(contentTraceFilter, SecurityContextPersistenceFilter.class)
                .addFilterAfter(httpTraceFilter, SecurityContextPersistenceFilter.class)
                .addFilterAfter(principalTraceFilter, FilterSecurityInterceptor.class)
                .requestMatcher(EndpointRequest.toAnyEndpoint())
                .authorizeRequests().anyRequest().permitAll();
    }
}
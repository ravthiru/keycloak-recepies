package com.lantana.keycloak.customtrace;

import java.util.List;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import org.springframework.boot.actuate.trace.http.HttpTraceEndpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@EndpointWebExtension(endpoint = HttpTraceEndpoint.class)
@ConditionalOnProperty(value = "management.trace.http.enabled", matchIfMissing = true)
public class HttTraceEndpointExtension {

    private CustomHttpTraceRepository repository;
        
    public HttTraceEndpointExtension(CustomHttpTraceRepository repository) {
        super();
        this.repository = repository;
    }

    @ReadOperation
    public RequestTraceDescriptor contents() {
        List<RequestTrace> traces = repository.findAllWithContent();
        return new RequestTraceDescriptor(traces);
    }
}

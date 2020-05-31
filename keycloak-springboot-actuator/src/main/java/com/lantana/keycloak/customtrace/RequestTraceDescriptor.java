package com.lantana.keycloak.customtrace;

import java.util.List;

public class RequestTraceDescriptor {

    protected List<RequestTrace> traces;

    public RequestTraceDescriptor(List<RequestTrace> traces) {
        super();
        this.traces = traces;
    }

    public List<RequestTrace> getTraces() {
        return traces;
    }

    public void setTraces(List<RequestTrace> traces) {
        this.traces = traces;
    }

}
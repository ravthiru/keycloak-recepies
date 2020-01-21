package com.lantana.keycloak.customtrace;

import org.springframework.boot.actuate.trace.http.HttpTrace;

public class RequestTrace {

    protected HttpTrace httpTrace;
    protected String requestBody;
    protected String responseBody;
    protected String principal;

    public RequestTrace() {

    }
    public RequestTrace(HttpTrace httpTrace) {
        this.httpTrace = httpTrace;
    }

    public String getPrincipal() {
        return principal;
    }

    public HttpTrace getHttpTrace() {
        return httpTrace;
    }

    public void setHttpTrace(HttpTrace httpTrace) {
        this.httpTrace = httpTrace;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public void setPrincipal(String authentication) {
        this.principal = authentication;

    }

}

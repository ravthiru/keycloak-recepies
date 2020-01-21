package com.lantana.keycloak.customtrace;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Component
@ConditionalOnProperty(value = "management.trace.http.enabled", matchIfMissing = true)
public class ContentTraceFilter extends OncePerRequestFilter {

    private TraceManager traceManager;

    @Value("${management.trace.http.tracebody:false}")
    private boolean traceBody;
    
    public ContentTraceFilter(TraceManager traceManager) {
        super();
        this.traceManager = traceManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (!traceBody) {
            filterChain.doFilter(request, response);
            return;
        }

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(
                request, 1000);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(
                response);
        try {
            filterChain.doFilter(wrappedRequest, wrappedResponse);
            traceManager.updateBody(wrappedRequest, wrappedResponse);
        } finally {
            wrappedResponse.copyBodyToResponse();
        }
    }
}

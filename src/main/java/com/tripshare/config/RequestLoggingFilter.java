package com.tripshare.config;

import org.slf4j.MDC;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

public class RequestLoggingFilter implements Filter {

    private static final String IP_ADDRESS_MDC_KEY = "ipAddress";
    public static final String REQUEST_ID_MDC_KEY = "requestId";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            if (request instanceof HttpServletRequest) {
                String ipAddress = ((HttpServletRequest) request).getRemoteAddr();
                MDC.put(IP_ADDRESS_MDC_KEY, !StringUtils.isEmpty(ipAddress)?ipAddress:"0.0.0.0");
            }
            MDC.put(REQUEST_ID_MDC_KEY, UUID.randomUUID().toString());
            chain.doFilter(request, response);
        } finally {
            MDC.remove(IP_ADDRESS_MDC_KEY);
        }
    }
}

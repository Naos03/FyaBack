package com.fya.fyaback.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitFilter implements Filter {

    private final Map<String, Long> requestCounts = new ConcurrentHashMap<>();
    private final Map<String, Long> windowStartTimes = new ConcurrentHashMap<>();

    private static final int MAX_REQUESTS = 20;
    private static final long TIME_WINDOW = 60000;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String clientIp = request.getRemoteAddr();
        long currentTime = System.currentTimeMillis();

        windowStartTimes.putIfAbsent(clientIp, currentTime);
        long startTime = windowStartTimes.get(clientIp);

        if (currentTime - startTime > TIME_WINDOW) {
            windowStartTimes.put(clientIp, currentTime);
            requestCounts.put(clientIp, 1L);
        } else {
            long count = requestCounts.getOrDefault(clientIp, 0L) + 1;
            if (count > MAX_REQUESTS) {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.setStatus(429); // Código HTTP Too Many Requests
                httpResponse.getWriter().write("Demasiadas peticiones. Por favor intente más tarde.");
                return;
            }
            requestCounts.put(clientIp, count);
        }

        chain.doFilter(request, response);
    }
}
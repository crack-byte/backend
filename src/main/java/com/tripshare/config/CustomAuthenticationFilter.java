package com.tripshare.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@AllArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationFilter.class);

//    private static final Map<String, String> validClient = new ConcurrentHashMap<>();
//
//    static {
//        validClient.put("clientName", "clientPassword");
//    }
    private final AuthenticationManager authenticationManager;
    private final JwtProcessor jwtProcessor;

    @SuppressWarnings("unchecked")
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException {
        try {
//            String clientHeader = request.getHeader("Authorization");
//            if (!StringUtils.containsWhitespace(clientHeader) || !clientHeader.startsWith("Basic")) {
//                throw new RuntimeException("unknown client");
//            }
//            String s = new String(Base64.getDecoder().decode(clientHeader.substring(6)));
//            String[] split = s.split(":");
//            if (split.length < 2 || !validClient.containsKey(split[0]) || !validClient.get(split[0]).equals(split[1])) {
//                throw new RuntimeException("invalid client");
//            }
            Map<String, String> creds = MAPPER.readValue(request.getInputStream(), Map.class);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(creds.get("username"),
                creds.get("password"), new ArrayList<>());
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //    @SneakyThrows
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication auth) {
        UserDetails principal = (UserDetails) auth.getPrincipal();
        String token = null;
        try {
            token = jwtProcessor.generateToken(principal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.addHeader("Authorization", token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        log.error("Authentication failed : {}", failed.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
    }
}

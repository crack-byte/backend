package com.tripshare.config;

import com.tripshare.entity.CustomUserDetails;
import com.tripshare.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Setter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProcessor {

    @Autowired
    private TokenService tokenService;
    private String secretKey;
    private long expiration;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        try {
            final Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String generateToken(CustomUserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", userDetails.getAuthorities());
        long currentTime = System.currentTimeMillis();
        String token = Jwts.builder().setClaims(claims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(currentTime))
            .setExpiration(new Date(currentTime + this.expiration))
            .signWith(SignatureAlgorithm.HS512, this.secretKey).compact();
        tokenService.saveToken(token, userDetails.getUser(), expiration);
        return token;
    }

    public CustomUserDetails validateTokenFromCache(String token) {
        if (isTokenExpired(token)) {
            return null;
        }
        return tokenService.validateToken(token);
    }
}

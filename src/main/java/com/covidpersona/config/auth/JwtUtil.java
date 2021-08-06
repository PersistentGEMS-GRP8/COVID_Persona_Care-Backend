package com.covidpersona.config.auth;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JwtUtil {
    private String secret;
    private int jwtExpirationInMs;

    @Value("${jwt.secret}")
    public void setSecret(String secret){
        this.secret = secret;
    }

    @Value("${jwt.jwtExpirationInMs}")
    public void setJwtExpirationInMs(int jwtExpirationInMs){
        this.jwtExpirationInMs = jwtExpirationInMs;
    }

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
        if (roles.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
            claims.put("isAdmin",true);
        }
        if (roles.contains(new SimpleGrantedAuthority("ROLE_HOSPITALADMIN"))){
            claims.put("isHospitalAdmin",true);
        }
        if (roles.contains(new SimpleGrantedAuthority("ROLE_MANAGER"))){
            claims.put("isManager",true);
        }

        return doGenerateToken(claims,userDetails.getUsername());
    }

    private String doGenerateToken(Map<String,Object> claims,String username ){
        return Jwts.builder().setClaims(claims).setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS512,secret).compact();
    }

    public boolean validateTokenMethod(String token){
        try{
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e){
            throw new BadCredentialsException("INVALID_CREDENTIALS",e);
        }catch (ExpiredJwtException e){
            throw e;
        }
    }

    public String getUsernameFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public List<SimpleGrantedAuthority> getRolesFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        List<SimpleGrantedAuthority> roles = null;

        Boolean isAdmin = claims.get("isAdmin",Boolean.class);
        Boolean isHospitalAdmin = claims.get("isHospitalAdmin",Boolean.class);
        Boolean isManager = claims.get("isManager",Boolean.class);

        if (isAdmin!=null && isAdmin){
            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        if (isHospitalAdmin!=null && isHospitalAdmin){
            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_HOSPITALADMIN"));
        }
        if (isManager!=null && isManager){
            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_MANAGER"));
        }
        return roles;
    }
}

/*
 * package com.hexaware.carrentalsystems.service;
 * 
 * import java.security.Key; import java.util.Date; import java.util.HashMap;
 * import java.util.Map; import java.util.function.Function;
 * 
 * import org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.stereotype.Service;
 * 
 * import io.jsonwebtoken.Claims; import io.jsonwebtoken.Jwts; import
 * io.jsonwebtoken.SignatureAlgorithm; import io.jsonwebtoken.io.Decoders;
 * import io.jsonwebtoken.security.Keys;
 * 
 * @Service public class JwtService {
 * 
 * // Use your own strong secret key (Base64 encoded) public static final String
 * SECRET = " e8ef574a9b4ab8a5260c5c1bffbdf8626a142496be791624f8c6e775993d951d";
 * 
 * // Generate token with claims and "name" as subject public String
 * createToken(Map<String, Object> claims, String email) { return Jwts.builder()
 * .setClaims(claims) .setSubject(email) // use 'name' instead of username
 * .setIssuedAt(new Date(System.currentTimeMillis())) .setExpiration(new
 * Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 60 mins expiry
 * .signWith(getSignKey(), SignatureAlgorithm.HS256) .compact(); }
 * 
 * private Key getSignKey() { byte[] keyBytes = Decoders.BASE64.decode(SECRET);
 * return Keys.hmacShaKeyFor(keyBytes); }
 * 
 * // Generate token given only the 'name' public String generateToken(String
 * email) { Map<String, Object> claims = new HashMap<>(); return
 * createToken(claims, email); }
 * 
 * // Extract all claims from token private Claims extractAllClaims(String
 * token) { return Jwts.parserBuilder() .setSigningKey(getSignKey()) .build()
 * .parseClaimsJws(token) .getBody(); }
 * 
 * // Extract specific claim using a resolver function public <T> T
 * extractClaim(String token, Function<Claims, T> claimsResolver) { final Claims
 * claims = extractAllClaims(token); return claimsResolver.apply(claims); }
 * 
 * // Extract 'name' (subject) from token public String extractUsername(String
 * token) { return extractClaim(token, Claims::getSubject); }
 * 
 * // Extract expiration date public Date extractExpiration(String token) {
 * return extractClaim(token, Claims::getExpiration); }
 * 
 * private Boolean isTokenExpired(String token) { return
 * extractExpiration(token).before(new Date()); }
 * 
 * // Validate token by checking name and expiry public Boolean
 * validateToken(String token, UserDetails userDetails) { final String email =
 * extractUsername(token); return (email.equals(userDetails.getUsername()) &&
 * !isTokenExpired(token)); } }
 */

package com.hexaware.carrentalsystems.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET = "e8ef574a9b4ab8a5260c5c1bffbdf8626a142496be791624f8c6e775993d951d";

    public String createToken(Map<String, Object> claims, String email) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email) // store email as subject
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) //24 hrs
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(String email) {
        return createToken(new HashMap<>(), email);
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String email = extractUsername(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}


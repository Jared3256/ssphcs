package com.shaninfosystem.SpecializedCare.service;

import com.shaninfosystem.SpecializedCare.entity.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.PrivateKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService
{
    private final String SECRET_KEY = "4bb6d1dfbafb64a681139d1586b6f1160d18159afd57c8c79136d7490630407c4bb6d1dfbafb64a681139d1586b6f1160d18159afd57c8c79136d7490630407c";

    public boolean isValid(String token, UserDetails user)
    {
        String username = getUserNameFromToken(token);

        return username.equals(user.getUsername()) && isTokenExpired((token));
    }

    public String getUserNameFromToken(String token)
    {
        return extractClaim(token, Claims::getSubject);
    }

    private boolean isTokenExpired(String token)
    {
        return extractExpiration(token).before(new Date());
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver)
    {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Date extractExpiration(String token)
    {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token)
    {
        return Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateToken(User user)
    {
        return Jwts
                .builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .signWith(getSigningKey())
                .issuer("Shan-infosystem")
                .encodePayload(true)
                .compact();
    }

    private SecretKey getSigningKey()
    {
        byte [] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}

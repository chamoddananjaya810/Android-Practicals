package lk.jiat.network.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

public class JwtUtil {
    private static  final String SECRET="TpwRzyul7NNBbTS2KfrMpNA0Fpif9n9jFLrxY/NMS+o=";

public static String genarateToken(String emaill){
   return Jwts.builder()
            .subject(emaill)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis()+5*60*1000))

            .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
            .compact();

}

public static String generateRefreshToken(String username){
    return Jwts.builder()
            .subject(username)
            .expiration(new Date(System.currentTimeMillis()+24*60 *60 *1000))
            .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
            .compact();
}

public static String validateToken(String token){
    return Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();//email
}
}

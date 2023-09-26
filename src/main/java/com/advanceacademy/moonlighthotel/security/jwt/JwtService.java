package com.advanceacademy.moonlighthotel.security.jwt;

//import com.advanceacademy.moonlighthotel.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

  private static final String SECRET_KEY = "*9b>B;P=2|-r`%2bZ_Svt9~\"Q>+!'#dzzO7!#KZCC3~uT$\")Gs&X4XY]b.rA-BE";

  public String extractUsername(String token){
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token){
    return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
  }

  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }


  private String generateToken(
          Map<String, Object> extraClaims,
          UserDetails userDetails)
  {
    return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000*60*24*10))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
  }

  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  //private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  //@Value("${bezkoder.app.jwtSecret}")
  //private String jwtSecret;
//
  //@Value("${bezkoder.app.jwtExpirationMs}")
  //private int jwtExpirationMs;
//
  //@Value("${bezkoder.app.jwtCookieName}")
  //private String jwtCookie;

//  public String getJwtFromCookies(HttpServletRequest request) {
//    Cookie cookie = WebUtils.getCookie(request, jwtCookie);
//    if (cookie != null) {
//      return cookie.getValue();
//    } else {
//      return null;
//    }
//  }
//
//  public ResponseCookie generateJwtCookie(UserDetailsImpl userPrincipal) {
//    String jwt = generateTokenFromEmail(userPrincipal.getEmail());
//    ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt).path("/api").maxAge(24 * 60 * 60).httpOnly(true).build();
//    return cookie;
//  }
//
//  public ResponseCookie getCleanJwtCookie() {
//    ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("/api").build();
//    return cookie;
//  }
//
//  public String getEmailFromJwtToken(String token) {
//    return Jwts.parserBuilder().setSigningKey(key()).build()
//        .parseClaimsJws(token).getBody().getSubject();
//  }
//
//  private Key key() {
//    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
//  }
//
//  public boolean validateJwtToken(String authToken) {
//    try {
//      Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
//      return true;
//    } catch (MalformedJwtException e) {
//      logger.error("Invalid JWT token: {}", e.getMessage());
//    } catch (ExpiredJwtException e) {
//      logger.error("JWT token is expired: {}", e.getMessage());
//    } catch (UnsupportedJwtException e) {
//      logger.error("JWT token is unsupported: {}", e.getMessage());
//    } catch (IllegalArgumentException e) {
//      logger.error("JWT claims string is empty: {}", e.getMessage());
//    }
//
//    return false;
//  }
//
//  public String generateTokenFromEmail(String email) {
//    return Jwts.builder()
//              .setSubject(email)
//              .setIssuedAt(new Date())
//              .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
//              .signWith(key(), SignatureAlgorithm.HS256)
//              .compact();
//  }
}

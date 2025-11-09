package ru.task.tracker.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    @Value(value = "${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expiration}")
    private long EXPIRATION_TIME;

    // Создание токена
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)              // Кого токен описывает
                .setIssuedAt(new java.util.Date())           // Когда выдан
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Когда истечёт
                .signWith(getSignKey())            // Подписываем секретом
                .compact();
    }

    // Получить имя пользователя из токена
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // Проверка: токен не просрочен и имя совпадает
    public boolean isTokenValid(String token, String username) {
        String extractedUsername = extractUsername(token);
        return extractedUsername.equals(username) && !isTokenExpired(token);
    }

    // Проверка срока действия
    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    // Получение всех "внутренностей" токена
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Ключ для подписи токена
    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
}
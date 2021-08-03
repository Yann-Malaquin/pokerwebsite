package fr.yannm.poker.security.jwt;

import fr.yannm.poker.security.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JwtUtils is the class that permit to work on the JWT.
 *
 * @author Yann
 * @version 1.0
 * @name : JwtUtils
 * @created 31 /07/2021 - 15:19
 * @project poker
 * @copyright Yann
 */
@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    /**
     * The secret.
     */
    @Value("${poker.app.jwtSecret}")
    @ApiModelProperty("The secret.")
    private String jwtSecret;

    @Value("${poker.app.jwtExpirationMs}")
    @ApiModelProperty("The expiration of the token.")
    private int jwtExpirationMs;

    /**
     * Generate the JWT string.
     *
     * @param authentication the authentication
     * @return the string
     * @since 1.0
     */
    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * Get the username from the token.
     *
     * @param token the token
     * @return the user name from jwt token
     * @since 1.0
     */
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Check if the token is valid.
     *
     * @param authToken the auth token
     * @return the boolean
     * @since 1.0
     */
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}

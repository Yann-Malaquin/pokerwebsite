package fr.yannm.poker.security.jwt;

import fr.yannm.poker.security.service.UserDetailsServiceImpl;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AuthTokenFilter is the class that filter the requests.
 *
 * @author Yann
 * @version 1.0
 * @name : AuthTokenFilter
 * @created 31 /07/2021 - 14:47
 * @project poker
 * @copyright Yann
 */
public class AuthTokenFilter extends OncePerRequestFilter {

    /**
     * Access to the class that permit to use the JWT.
     */
    @Autowired
    @ApiModelProperty("Access to the class that permit to use the JWT.")
    private JwtUtils jwtUtils;

    /**
     * Access to the class that permit to use the loadUserByUsername.
     */
    @Autowired
    @ApiModelProperty("Access to the class that permit to use the loadUserByUsername.")
    private UserDetailsServiceImpl userDetailsService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            // Get the authorization header by removing Bearer prefix.
            String jwt = parseJwt(httpServletRequest);

            // If the request has a JWT and is valid then
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                // Getting the username
                String username = jwtUtils.getUserNameFromJwtToken(jwt);

                // Get UserDetails from username
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                // Creates an Authentication object
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                // Setting the current UserDetais in SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception e) {
            LOGGER.error("Cannot set user authentication: {}", e);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    /**
     * Parse the header of a request.
     *
     * @param httpServletRequest
     * @return a string
     * @since 1.0
     */
    private String parseJwt(HttpServletRequest httpServletRequest) {
        String headerAuth = httpServletRequest.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer")) {
            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }
}

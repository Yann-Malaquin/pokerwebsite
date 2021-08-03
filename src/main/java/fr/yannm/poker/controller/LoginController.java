package fr.yannm.poker.controller;

import fr.yannm.poker.payload.request.LoginRequest;
import fr.yannm.poker.payload.response.JwtResponse;
import fr.yannm.poker.repository.RoleRepository;
import fr.yannm.poker.repository.UserRepository;
import fr.yannm.poker.security.jwt.JwtUtils;
import fr.yannm.poker.security.service.UserDetailsImpl;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LoginController is the class that refers to the login of a user.
 *
 * @author Yann
 * @version 1.0
 * @name : LoginController
 * @created 02 /08/2021 - 14:02
 * @project poker
 * @copyright Yann
 */
@RestController
@CrossOrigin
public class LoginController {

    /**
     * The Authentication manager.
     */
    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * The interface used to query on the table user.
     */
    @Autowired
    UserRepository userRepository;

    /**
     * The interface used to query on the table role.
     */
    @Autowired
    RoleRepository roleRepository;

    /**
     * Interface used to encode a password.
     */
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * The class JwtUtils to provides method for generating, parsing, validating JWT.
     */
    @Autowired
    JwtUtils jwtUtils;

    /**
     * The route to authenticate a user.
     *
     * @param loginRequest the form with the username and the password
     * @return the response entity
     * @since 1.0
     */
    @PostMapping("/signin")
    @ApiModelProperty("The route to authenticate a user.")
    public ResponseEntity<?> autenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        // Use to authenticate a login account
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        // Get the session
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Generation of the token
        String jwt = jwtUtils.generateJwtToken(authentication);

        // Cast to UserDetailsImpl
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Get the roles of the request
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        // Return the response
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(),
                userDetails.getUsername(), userDetails.getEmail(), roles));
    }
}

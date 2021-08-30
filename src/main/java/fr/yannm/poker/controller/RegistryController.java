package fr.yannm.poker.controller;

import fr.yannm.poker.model.ERole;
import fr.yannm.poker.model.Role;
import fr.yannm.poker.model.Scoreboard;
import fr.yannm.poker.model.User;
import fr.yannm.poker.payload.request.SignupRequest;
import fr.yannm.poker.payload.response.MessageResponse;
import fr.yannm.poker.repository.RoleRepository;
import fr.yannm.poker.repository.ScoreboardRepository;
import fr.yannm.poker.repository.UserRepository;
import fr.yannm.poker.security.jwt.JwtUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

/**
 * RegistryController is the controller that refers to the login of a user.
 *
 * @author Yann
 * @version 1.0
 * @name : RegistryController
 * @created 31/07/2021 - 15:33
 * @project poker
 * @copyright Yann
 **/
@CrossOrigin
@RestController
public class RegistryController {

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
     * The interface used to query on the table scoreboard.
     */
    @Autowired
    ScoreboardRepository scoreboardRepository;

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
     * The route of the registration of an user.
     *
     * @param signUpRequest the form with the different field request.
     * @return the response entity
     */
    @PostMapping("signup")
    @ApiOperation("The route of the registration of an user.")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        // If the username is already prest in the database
        if (Boolean.TRUE.equals(userRepository.existsByUsername(signUpRequest.getUsername()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken"));
        }

        // If the email is already prest in the database
        if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()),
                0);
        Scoreboard scoreboard = new Scoreboard();

        // Setting the roles of the user
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        String error = "Error: Role is not found.";

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException(error));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException(error));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException(error));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException(error));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        // Save in the database
        userRepository.save(user);

        scoreboard.setUser(user);
        scoreboard.setRank(Math.toIntExact(user.getId()));
        scoreboardRepository.save(scoreboard);

        // Return the message of the registration if it is ok.
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}

package fr.yannm.poker.controller;

import fr.yannm.poker.model.User;
import fr.yannm.poker.payload.request.ModifyRequest;
import fr.yannm.poker.payload.request.RecoveryRequest;
import fr.yannm.poker.payload.response.MessageResponse;
import fr.yannm.poker.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * PasswordController is the controller that refers to the password (recovery, modification).
 *
 * @author Yann
 * @version 1.0
 * @name : PasswordController
 * @created 04 /08/2021 - 14:15
 * @project poker
 * @copyright Yann
 */
@RestController
@CrossOrigin
public class PasswordController {

    /**
     * The interface used to query on the table user.
     */
    @Autowired
    UserRepository userRepository;

    /**
     * Interface used to encode a password.
     */
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Routes used to change the password if the user forgot it.
     *
     * @param recoveryRequest the form to recover
     * @return the response entity
     * @since 1.0
     */
    @PutMapping("/recovery")
    @ApiOperation("The route used to recover a password.")
    public ResponseEntity<?> recovery(@RequestBody @Valid RecoveryRequest recoveryRequest) {

        User user = null;
        Optional<User> userOptional;

        // Check if the email is correct
        if (Boolean.TRUE.equals(userRepository.existsByEmail(recoveryRequest.getEmail()))) {
            userOptional = userRepository.findByEmail(recoveryRequest.getEmail());
            if (userOptional.isPresent()) {
                user = userOptional.get();
            }
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email does not exist"));
        }

        // Modify the password.
        if (user != null) {
            user.setPassword(passwordEncoder.encode(recoveryRequest.getPassword()));
            userRepository.save(user);
        }


        return ResponseEntity.ok(new MessageResponse("Password changed with success !"));
    }

    /**
     * Routes used to change the password.
     *
     * @param modifyRequest the form to recover
     * @return the response entity
     * @since 1.0
     */
    @PutMapping("/{id}/modify")
    @ApiOperation("The route used to modify a password.")
    public ResponseEntity<?> modify(@PathVariable("id") Long id, @RequestBody @Valid ModifyRequest modifyRequest) {

        User user;
        Optional<User> userOptional;

        // Check if the user exists
        userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: User does not exist"));
        }

        // Modify the password.
        user.setPassword(passwordEncoder.encode(modifyRequest.getPassword()));
        userRepository.save(user);


        return ResponseEntity.ok(new MessageResponse("Password changed with success !"));
    }

}


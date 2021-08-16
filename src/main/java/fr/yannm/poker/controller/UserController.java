package fr.yannm.poker.controller;

import com.github.javafaker.Faker;
import fr.yannm.poker.model.ERole;
import fr.yannm.poker.model.Role;
import fr.yannm.poker.model.User;
import fr.yannm.poker.payload.request.ProfileRequest;
import fr.yannm.poker.payload.request.WalletRequest;
import fr.yannm.poker.payload.response.MessageResponse;
import fr.yannm.poker.repository.RoleRepository;
import fr.yannm.poker.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

/**
 * UserController is the controller that refers to the user.
 *
 * @author Yann
 * @version 1.0
 * @name : UserController
 * @created 04 /08/2021 - 15:17
 * @project poker
 * @copyright Yann
 */
@RestController
@CrossOrigin
public class UserController {

    /**
     * The interface used to query on the table user.
     */
    @Autowired
    UserRepository userRepository;

    Faker faker = new Faker(new Locale("fr"));

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * The error message for the non duplication.
     */
    private static final String ERRORMESSAGE = "Error: User does not exist !";

    /**
     * Gets user by id.
     *
     * @param id the id of the user
     * @return the user
     * @since 1.0
     */
    @GetMapping("/user/{id}")
    @ApiOperation("The route permits to get a user by its id.")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        User user;
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            user = userOptional.get();
            return ResponseEntity.ok(user);
        }

        return ResponseEntity
                .badRequest()
                .body(new MessageResponse(ERRORMESSAGE));
    }

    @GetMapping("/createUsers")
    @ApiOperation("The route permits to get a user by its id.")
    public ResponseEntity<?> createUsers() {
        User user;
        Set<Role> roles = new HashSet<>();
        String error = "Error: Role is not found.";
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException(error));
        roles.add(userRole);

        for (int i = 0; i < 10; i++) {
            user = new User();
            user.setUsername("user" + i);
            user.setPassword(passwordEncoder.encode("123456"));
            user.setEmail("user" + i + "@gmail.com");
            user.setWallet(faker.number().numberBetween(1, 2500));
            user.setScore(faker.number().numberBetween(1, 10000));
            user.setWin(String.valueOf(faker.number().numberBetween(1, 100)));
            user.setLost(String.valueOf(faker.number().numberBetween(1, 100)));
            user.setRatio();
            user.setRoles(roles);

            userRepository.save(user);
        }

        return ResponseEntity.ok(new MessageResponse("Users created with success !"));
    }

    /**
     * Gets all the users.
     *
     * @return the users
     * @since 1.0
     */
    @GetMapping("/users")
    @ApiOperation("The route permits to get the whole users.")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    /**
     * Add money to the wallet of the user
     *
     * @param id            the id of the user
     * @param walletRequest the form
     * @return the response entity
     * @since 1.0
     */
    @ApiOperation("The route permits to add money to the wallet of the user.")
    @PutMapping("/{id}/add_money")
    public ResponseEntity<?> addMoney(@PathVariable("id") Long id, @RequestBody @Valid WalletRequest walletRequest) {

        User user;
        int walletTemp;
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(ERRORMESSAGE));
        }

        // Store the current wallet
        walletTemp = user.getWallet();
        // Make the addition between the current wallet and the amount
        user.setWallet(walletTemp + walletRequest.getAmount());

        // Save the user
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Money added with success !"));
    }

    /**
     * Substract money to the wallet of the user.
     *
     * @param id            the id of the user
     * @param walletRequest the form
     * @return the response entity
     * @since 1.0
     */
    @PutMapping("/{id}/substract_money")
    @ApiOperation("The route permits to substract the money from the wallet of the user.")
    public ResponseEntity<?> substractMoney(@PathVariable("id") Long id, @RequestBody @Valid WalletRequest walletRequest) {

        User user;
        int walletTemp;
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(ERRORMESSAGE));
        }

        // Store the current wallet
        walletTemp = user.getWallet();
        // Make the substraction between the current wallet and the amount
        user.setWallet(walletTemp - walletRequest.getAmount());

        // Save the user
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Money substracted with success !"));
    }

    /**
     * Update the profile of the user
     *
     * @param id             the id of the user
     * @param profileRequest the form
     * @return the response entity
     */
    @PutMapping("{id}/updateProfile")
    public ResponseEntity<?> updateProfile(@PathVariable("id") Long id,
                                           @Valid @RequestBody ProfileRequest profileRequest) {

        Optional<User> userOptional = userRepository.findById(id);
        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(ERRORMESSAGE));
        }

        // Check if the username is already taken
        if (userRepository.existsByUsername(profileRequest.getUsername())) {
            return ResponseEntity.
                    badRequest()
                    .body(new MessageResponse("Username already taken"));
        }

        // Setting the new username
        user.setUsername(profileRequest.getUsername());

        // Save the user
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Profile updated with success !"));


    }


}

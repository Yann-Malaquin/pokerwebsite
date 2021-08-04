package fr.yannm.poker.controller;

import fr.yannm.poker.model.User;
import fr.yannm.poker.payload.request.WalletRequest;
import fr.yannm.poker.payload.response.MessageResponse;
import fr.yannm.poker.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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


}

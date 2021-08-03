package fr.yannm.poker.repository;

import fr.yannm.poker.model.User;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * UserRepository is the interface where to find the different query for the entity User.
 *
 * @author Yann
 * @version 1.0
 * @name : UserRepository
 * @created 29/07/2021 - 15:54
 * @project poker
 * @copyright Yann
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by its id.
     *
     * @param id
     * @return an optional user
     * @since 1.0
     */
    @ApiModelProperty("Find a user by its id.")
    Optional<User> findById(Long id);

    /**
     * Find a user by its username.
     *
     * @param username
     * @return an optional user
     * @since 1.0
     */
    @ApiModelProperty("Find a user by its username.")
    Optional<User> findByUsername(String username);

    /**
     * Find if a user already exists by its username.
     *
     * @param username
     * @return an optional user
     * @since 1.0
     */
    @ApiModelProperty("Find if a user already exists by its username.")
    Boolean existsByUsername(String username);

    /**
     * Find if a user already exists by its email.
     *
     * @param email
     * @return an optional user
     * @since 1.0
     */
    @ApiModelProperty("Find if a user already exists by its email.")
    Boolean existsByEmail(String email);
}

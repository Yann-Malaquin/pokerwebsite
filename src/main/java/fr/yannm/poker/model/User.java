package fr.yannm.poker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * User is the entity that represents a user with :
 * <ul>
 *     <li>An id</li>
 *     <li>A username</li>
 *     <li>An email</li>
 *     <li>A password</li>
 *     <li>A wallet</li>
 *     <li>A score</li>
 *     <li>An amount of win</li>
 *     <li>An amount of lost</li>
 *     <li>An ratio w/l</li>
 * </ul>
 *
 * @author Yann
 * @version 1.0
 * @name : User
 * @created 28/07/2021 - 14:08
 * @project poker
 * @copyright Yann
 **/
@Entity
@Table(name = "users")
public class User {

    /**
     * The id of the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("The ID of the user.")
    @Getter
    private Long id;

    /**
     * The username of the user. Must be unique.
     */
    @NotNull(message = "Username is required.")
    @Column(unique = true)
    @ApiModelProperty("The username of the user. Must be unique.")
    @Getter
    @Setter
    private String username;

    /**
     * The email of the user. Must be unique.
     */
    @Email
    @NotNull(message = "Email is required.")
    @Column(unique = true)
    @ApiModelProperty("The email of the user. Must be unique.")
    @Getter
    @Setter
    private String email;

    /**
     * The password of the user.
     */
    @Getter
    @Setter
    @ApiModelProperty("The password of the user.")
    private String password;

    /**
     * The wallet of the user.
     */
    @ApiModelProperty("The wallet of the user.")
    @Getter
    @Setter
    private int wallet = 0;

    /**
     * The scoreboard of the user.
     */
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    @JsonIgnoreProperties({"user"})
    @ApiModelProperty("The scoreboard of the user.")
    @Getter
    @Setter
    private Scoreboard scoreboard;

    /**
     * The role of the user.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Getter
    @Setter
    @ApiModelProperty("The role of the user.")
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param email    the email
     * @param password the password
     * @param wallet   the wallet
     */
    public User(String username,
                String email,
                String password,
                int wallet) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.wallet = wallet;
    }

}

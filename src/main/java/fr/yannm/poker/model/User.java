package fr.yannm.poker.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
     * The score of the user, in other word the total amount won.
     */
    @ApiModelProperty("The score of the user, in other word the total amount won.")
    @Getter
    @Setter
    private int score = 0;

    /**
     * The number of game won.
     */
    @ApiModelProperty("The number of game won.")
    @Getter
    @Setter
    private String win = "0";

    /**
     * The number of game lost.
     */
    @ApiModelProperty("The number of game lost.")
    @Getter
    @Setter
    private String lost = "0";

    /**
     * The ratio won/lost.
     */
    @ApiModelProperty("The ratio won/lost.")
    @Getter
    private double ratio = 0.0;

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
     * @param score    the score
     * @param win      the win
     * @param lost     the lost
     * @param ratio    the ratio
     */
    public User(String username,
                String email,
                String password,
                int wallet,
                int score,
                String win,
                String lost,
                double ratio) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.wallet = wallet;
        this.score = score;
        this.win = win;
        this.lost = lost;
        this.ratio = ratio;
    }

    public void setRatio() {
        this.ratio = new BigDecimal(win)
                .divide(new BigDecimal(lost),2, RoundingMode.UP)
                .doubleValue();
    }
}

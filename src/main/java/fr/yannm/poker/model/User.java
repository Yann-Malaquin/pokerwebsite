package fr.yannm.poker.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * @author Yann
 * @version 1.0
 * @name : User
 * @created 28/07/2021 - 14:08
 * @project poker
 * @copyright Yann
 **/
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
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
    private int win = 0;

    /**
     * The number of game lost.
     */
    @ApiModelProperty("The number of game lost.")
    @Getter
    @Setter
    private int lost = 0;

    /**
     * The ratio won/lost.
     */
    @ApiModelProperty("The ratio won/lost.")
    @Getter
    @Setter
    private double ratio = 0.0;
}

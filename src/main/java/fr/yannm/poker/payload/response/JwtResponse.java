package fr.yannm.poker.payload.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * JwtResponse is the class that used to create the response to the format JWT with :
 * <ul>
 *     <li>A token</li>
 *     <li>A type</li>
 *     <li>An id</li>
 *     <li>A username</li>
 *     <li>An email</li>
 *     <li>A list of roles</li>
 * </ul>
 *
 * @author Yann
 * @version 1.0
 * @name : JwtReponse
 * @created 31/07/2021 - 15:39
 * @project poker
 * @copyright Yann
 **/
public class JwtResponse {

    /**
     * The token of the response.
     */
    @Getter
    @Setter
    @ApiModelProperty("The token of the response.")
    private String token;

    /**
     * The type of the response.
     */
    @Getter
    @Setter
    @ApiModelProperty("The type of the response.")
    private String type = "Bearer";

    /**
     * The id of the response.
     */
    @Getter
    @Setter
    @ApiModelProperty("The id of the response")
    private Long id;

    /**
     * The username of the user if the response doesn't failed.
     */
    @Getter
    @Setter
    @ApiModelProperty("The username of the user if the response doesn't failed.")
    private String username;

    /**
     * The email of the user if the response doesn't failed.
     */
    @Getter
    @Setter
    @ApiModelProperty("The email of the user if the response doesn't failed.")
    private String email;

    /**
     * The list of roles of the user if the response doesn't failed.
     */
    @Getter
    @Setter
    @ApiModelProperty("The list of roles of the user if the response doesn't failed.")
    private List<String> roles;

    /**
     * The score of the user, in other word the total amount won.
     */
    @ApiModelProperty("The score of the user, in other word the total amount won.")
    @Getter
    @Setter
    private int score;

    /**
     * The number of game won.
     */
    @ApiModelProperty("The number of game won.")
    @Getter
    @Setter
    private int win;

    /**
     * The number of game lost.
     */
    @ApiModelProperty("The number of game lost.")
    @Getter
    @Setter
    private int lost;

    /**
     * The ratio won/lost.
     */
    @ApiModelProperty("The ratio won/lost.")
    @Getter
    @Setter
    private double ratio;

    /**
     * Instantiates a new Jwt response.
     *
     * @param accessToken the access token
     * @param id          the id
     * @param username    the username
     * @param email       the email
     * @param score       the score
     * @param win         the win
     * @param lost        the lost
     * @param ratio       the ratio
     * @param roles       the roles
     * @since 1.0
     */
    public JwtResponse(String accessToken,
                       Long id,
                       String username,
                       String email,
                       int score,
                       int win,
                       int lost,
                       double ratio,
                       List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.score = score;
        this.win = win;
        this.lost = lost;
        this.ratio = ratio;
        this.roles = roles;
    }
}

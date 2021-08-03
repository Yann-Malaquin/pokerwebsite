package fr.yannm.poker.payload.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * LoginRequest is the class that refer to the form of the login with :
 * <ul>
 *     <li>A username</li>
 *     <li>A password</li>
 * </ul>
 *
 *
 * @author Yann
 * @version 1.0
 * @name : LoginRequest
 * @created 02/08/2021 - 14:00
 * @project poker
 * @copyright Yann
 **/
public class LoginRequest {

    /**
     * The username of the user. Must not be blank.
     */
    @NotBlank
    @Getter
    @Setter
    @ApiModelProperty("The username of the user. Must not be blank.")
    private String username;

    /**
     * The password of the user. Must not be blank.
     */
    @NotBlank
    @Getter
    @Setter
    @ApiModelProperty("The password of the user. Must not be blank.")
    private String password;
}

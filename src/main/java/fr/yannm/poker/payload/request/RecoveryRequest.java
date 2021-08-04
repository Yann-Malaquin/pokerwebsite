package fr.yannm.poker.payload.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * RecoveryRequest is the class that permits a user to ask new password if he forgot it with :
 * <ul>
 *     <li>An email</li>
 *     <li>A password</li>
 * </ul>
 *
 * @author Yann
 * @version 1.0
 * @name : RecoveryRequest
 * @created 04/08/2021 - 14:11
 * @project poker
 * @copyright Yann
 **/
public class RecoveryRequest {

    /**
     * The email of the user.
     */
    @Email
    @NotBlank
    @Getter
    @Setter
    @ApiModelProperty("The email of the user.")
    private String email;

    /**
     * The new password.
     */
    @NotBlank
    @Getter
    @Setter
    @ApiModelProperty("The new password.")
    private String password;
}

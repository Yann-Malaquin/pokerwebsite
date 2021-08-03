package fr.yannm.poker.payload.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * SignupRequest is the class that refer to the form of the registration with :
 * <ul>
 *     <li>A username</li>
 *     <li>An email</li>
 *     <li>A list of roles</li>
 *     <li>A password</li>
 * </ul>
 *
 * @author Yann
 * @version 1.0
 * @name : SignupRequest
 * @created 31/07/2021 - 15:37
 * @project poker
 * @copyright Yann
 **/
public class SignupRequest {

    /**
     * The username of the user. Must not be null.
     */
    @NotBlank
    @Size(min = 3, max = 20)
    @Getter
    @Setter
    @ApiModelProperty("The username of the user. Must not be null.")
    private String username;

    /**
     * The email of the user. Must not be null.
     */
    @NotBlank
    @Size(max = 50)
    @Email
    @Getter
    @Setter
    @ApiModelProperty("The email of the user. Must not be null.")
    private String email;

    /**
     * The list of roles of the user.
     */
    @Getter
    @Setter
    @ApiModelProperty("The list of roles of the user.")
    private Set<String> role;

    /**
     * The password of the user. Must not be null.
     */
    @NotBlank
    @Size(min = 6, max = 40)
    @Getter
    @Setter
    @ApiModelProperty("The password of the user. Must not be null.")
    private String password;
}

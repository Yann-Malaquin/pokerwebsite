package fr.yannm.poker.payload.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * ProfileRequest is the class that permits a user to modify his own profile with :
 * <ul>
 *     <li>A username</li>
 * </ul>
 * @author Yann
 * @version 1.0
 * @name : ProfileRequest
 * @created 14/08/2021 - 14:31
 * @project poker
 * @copyright Yann
 **/
public class ProfileRequest {

    /**
     * The new username.
     */
    @NotBlank
    @Getter
    @Setter
    @ApiModelProperty("The new username.")
    private String username;

}

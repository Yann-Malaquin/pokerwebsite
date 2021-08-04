package fr.yannm.poker.payload.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * ModifyRequest is the class that permits a user to modify his own password with :
 * <ul>
 *     <li>A password</li>
 * </ul>
 *
 * @author Yann
 * @version 1.0
 * @name : ModifyRequest
 * @created 04/08/2021 - 14:49
 * @project poker
 * @copyright Yann
 **/
public class ModifyRequest {

    /**
     * The new password.
     */
    @NotBlank
    @Getter
    @Setter
    @ApiModelProperty("The new password.")
    private String password;

}

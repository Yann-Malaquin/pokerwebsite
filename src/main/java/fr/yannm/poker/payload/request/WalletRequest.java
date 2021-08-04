package fr.yannm.poker.payload.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * WalletRequest is the class that permits a user to add or to substract with :
 * <ul>
 *     <li>An amount</li>
 * </ul>
 *
 * @author Yann
 * @version 1.0
 * @name : WalletRequest
 * @created 04/08/2021 - 15:18
 * @project poker
 * @copyright Yann
 **/
public class WalletRequest {

    /**
     * The amount to add or to substract.
     */
    @NotBlank
    @ApiModelProperty("The amount to add or to substract.")
    @Getter
    @Setter
    private int amount;
}

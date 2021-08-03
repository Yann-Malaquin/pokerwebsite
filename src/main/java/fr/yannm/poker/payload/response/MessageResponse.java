package fr.yannm.poker.payload.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * MessageResponse is the class that permit to customize the response message with:
 * <ul>
 *     <li>A message</li>
 * </ul>
 *
 * @author Yann
 * @version 1.0
 * @name : MessageResponse
 * @created 31/07/2021 - 15:40
 * @project poker
 * @copyright Yann
 **/
public class MessageResponse {

    /**
     * The message of the response.
     */
    @Getter
    @Setter
    @ApiModelProperty("The message of the response.")
    private String message;

    /**
     * Instantiates a new Message response.
     *
     * @param message the message
     * @since 1.0
     */
    public MessageResponse(String message) {
        this.message = message;
    }

}

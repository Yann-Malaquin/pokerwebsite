package fr.yannm.poker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Yann
 * @version 1.0
 * @name : Scoreboard
 * @created 26/08/2021 - 14:18
 * @project poker
 * @copyright Yann
 **/
@Entity
@Table(name = "scoreboards")
public class Scoreboard {

    /**
     * The id of the scoreboard.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("The ID of the user.")
    @Getter
    private Long id;

    /**
     * The rank of the user.
     */
    @ApiModelProperty("The rank of the user.")
    @Getter
    @Setter
    private int rank;

    /**
     * The place lost or won by the user.
     */
    @ApiModelProperty("The place lost or won by the user.")
    @Getter
    @Setter
    private int place;

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
     * The user to which is the scoreboard is attached.
     */
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"scoreboard"})
    @ApiModelProperty("The user to which is the scoreboard is attached.")
    @Getter
    @Setter
    @JsonIgnore
    private User user;

    public Scoreboard() {
    }

    public Scoreboard(int rank,
                      int place,
                      int score,
                      String win,
                      String lost,
                      double ratio) {
        this.rank = rank;
        this.place = place;
        this.score = score;
        this.win = win;
        this.lost = lost;
        this.ratio = ratio;
    }

    public void setRatio() {
        this.ratio = new BigDecimal(win)
                .divide(new BigDecimal(lost), 2, RoundingMode.UP)
                .doubleValue();
    }
}

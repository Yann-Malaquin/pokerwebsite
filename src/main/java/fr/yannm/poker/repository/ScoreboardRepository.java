package fr.yannm.poker.repository;

import fr.yannm.poker.model.Scoreboard;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Yann
 * @version 1.0
 * @name : ScoreboardInterface
 * @created 26/08/2021 - 14:55
 * @project poker
 * @copyright Yann
 **/
public interface ScoreboardRepository extends JpaRepository<Scoreboard, Long> {

    /**
     * Find a scoreboard by its id.
     *
     * @param id the id of the scoreboard
     * @return an optional scoreboard
     * @since 1.0
     */
    @ApiModelProperty("Find a user by its id.")
    Optional<Scoreboard> findById(Long id);

    List<Scoreboard> findByOrderByRankAsc();

    List<Scoreboard> findByOrderByScoreDesc();



}

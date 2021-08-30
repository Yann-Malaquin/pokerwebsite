package fr.yannm.poker.security.service;

import fr.yannm.poker.model.Scoreboard;
import fr.yannm.poker.model.User;
import fr.yannm.poker.repository.ScoreboardRepository;
import fr.yannm.poker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yann
 * @version 1.0
 * @name : ScoreboardService
 * @created 28/08/2021 - 15:00
 * @project poker
 * @copyright Yann
 **/
@Service
public class ScoreboardService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ScoreboardRepository scoreboardRepository;

    public void updateScoreboard() {

        List<Scoreboard> scoreboardList = new ArrayList<>();
        scoreboardList = scoreboardRepository.findByOrderByScoreDesc();
        User user;
        Scoreboard scoreboard;
        int previousRank;
        int place;

        for (int i = 0; i < scoreboardList.size(); i++) {
            user = scoreboardList.get(i).getUser();
            scoreboard = user.getScoreboard();
            previousRank = scoreboard.getRank();

            if(previousRank > (i+1)){
                place = previousRank - (i+1);
            } else if(previousRank < (i+1)){
                place = previousRank - (i+1);
            } else {
                place = 0;
            }

            scoreboard.setRank(i+1);
            scoreboard.setPlace(place);

            scoreboardRepository.save(scoreboard);

        }

    }
}

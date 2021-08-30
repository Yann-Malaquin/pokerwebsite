package fr.yannm.poker.controller;

import fr.yannm.poker.repository.ScoreboardRepository;
import fr.yannm.poker.security.service.ScoreboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yann
 * @version 1.0
 * @name : ScoreboardController
 * @created 27/08/2021 - 14:16
 * @project poker
 * @copyright Yann
 **/
@RestController
@CrossOrigin
public class ScoreboardController {

    @Autowired
    ScoreboardRepository scoreboardRepository;

    @Autowired
    ScoreboardService scoreboardService;

    @GetMapping("/scoreboards")
    public void getScoreboards(){
        scoreboardService.updateScoreboard();
    }

}

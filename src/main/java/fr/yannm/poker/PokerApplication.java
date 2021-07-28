package fr.yannm.poker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@Controller
@SpringBootApplication
public class PokerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PokerApplication.class, args);
    }

}

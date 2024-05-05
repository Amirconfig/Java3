package ca.sheridancollege.khanmoam.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.khanmoam.model.VideoGame;

@Controller
public class GameController {

    // Create a global List capable of storing your video game instances in a Thread safe fashion.
    private List<VideoGame> gameList = Collections.synchronizedList(new ArrayList<>());

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("game", new VideoGame());

        model.addAttribute("gameList", gameList);

        return "index";
    }

    @PostMapping("/addGame")
    public String addGame(@ModelAttribute("game") VideoGame newGame, Model model) {
        gameList.add(newGame);

        model.addAttribute("game", new VideoGame());

        model.addAttribute("gameList", gameList);

        return "index";
    }
}

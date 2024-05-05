package ca.sheridancollege.khanmoam.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/hairColour")
    public String hairColour() {
        return "hairColour";
    }

    @GetMapping("/perfectHeight")
    public String perfectHeight() {
        return "perfectHeight";
    }

    @GetMapping("/preferredName")
    public String preferredName() {
        return "preferredName";
    }

    // This method handles requests to the index page
    @GetMapping("/")
    public String index() {
        return "index";
    }
}


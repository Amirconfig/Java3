package ca.sheridancollege.khanmoam.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/secured/user")
    public String goUser() {
        return "/secured/user/index";
    }

    @GetMapping("/check")
    public String check() {
        return "check";
    }
}

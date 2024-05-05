package ca.sheridancollege.khanmoam.controllers;

import ca.sheridancollege.khanmoam.models.Phone;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PhoneController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("phone", new Phone());
        return "index";
    }

    @PostMapping("/insertPhone")
    public String addPhone(@ModelAttribute Phone phone, HttpSession session) {
        List<Phone> phones;
        if(session.getAttribute("phoneList") == null) {
            phones = new ArrayList<>();
        } else {
            phones = (List<Phone>) session.getAttribute("phoneList");
        }

        phones.add(phone);
        session.setAttribute("phoneList", phones);
        
        return "redirect:/";
    }
}

package ca.sheridancollege.khanmoam.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class for handling login-related operations.
 */
@Controller
public class LoginController {

	
	/**
	 * Displays the login page with an error message if provided.
	 * 
	 * @param model The model to be used for rendering the view.
	 * @param error The error message to be displayed (if any).
	 * @return The view name for displaying the login page.
	 */
	@GetMapping("/login")
	public String login(Model model, String error) {
		if (error != null)
			model.addAttribute("error", "Your username or password is invalid.");
		return "login";
	}
}
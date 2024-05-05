package ca.sheridancollege.khanmoam.controllers;

import ca.sheridancollege.khanmoam.beans.Book;
import ca.sheridancollege.khanmoam.database.DatabaseAccess;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Controller class for handling main page and permission-denied operations.
 */
@Controller
public class MainController {

	private final DatabaseAccess databaseAccess;


	/**
	 * Constructs a new MainController with the specified DatabaseAccess.
	 * 
	 * @param databaseAccess The database access object.
	 */
	public MainController(DatabaseAccess databaseAccess) {
		this.databaseAccess = databaseAccess;
	}

	/**
	 * Displays the main page with a list of all books.
	 * 
	 * @param model The model to be used for rendering the view.
	 * @return The view name for displaying the main page.
	 */
	@GetMapping({ "/", "/index.html" })
	public String index(Model model) {
		List<Book> books = databaseAccess.getAllBooks();
		model.addAttribute("books", books);
		return "index";
	}

	/**
	 * Displays the permission-denied error page.
	 * 
	 * @param model The model to be used for rendering the view.
	 * @return The view name for displaying the permission-denied error page.
	 */
	@GetMapping({ "permission-denied" })
	public String permissionDenied(Model model) {
		return "error/permissionDenied";
	}
}
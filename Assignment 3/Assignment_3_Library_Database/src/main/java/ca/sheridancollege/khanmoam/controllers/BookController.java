package ca.sheridancollege.khanmoam.controllers;

import ca.sheridancollege.khanmoam.beans.Book;
import ca.sheridancollege.khanmoam.database.DatabaseAccess;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller class for handling book-related operations.
 */
@Controller
public class BookController {

	private final DatabaseAccess databaseAccess;

	/**
	 * Constructs a new BookController with the specified DatabaseAccess.
	 * 
	 * @param databaseAccess The database access object.
	 */
	public BookController(DatabaseAccess databaseAccess) {
		this.databaseAccess = databaseAccess;
	}

	/**
	 * Displays the form for adding a new book.
	 * 
	 * @param model The model to be used for rendering the view.
	 * @return The view name for displaying the add-book form.
	 */
	@GetMapping("/add/book")
	public String showAddBookForm(Model model) {
		Book bookForm = new Book();
		model.addAttribute("bookForm", bookForm);

		return "admin/add-book";
	}

	/**
	 * Handles the submission of the add-book form. Adds the book to the database
	 * and redirects to the index page.
	 * 
	 * @param bookForm The Book object submitted through the form.
	 * @param model    The model to be used for rendering the view.
	 * @return The redirect URL to the index page.
	 */
	@PostMapping("/add/book")
	public String addBook(@ModelAttribute("bookForm") Book bookForm, Model model) {
		databaseAccess.addBook(bookForm);
		return "redirect:/index.html";
	}
}
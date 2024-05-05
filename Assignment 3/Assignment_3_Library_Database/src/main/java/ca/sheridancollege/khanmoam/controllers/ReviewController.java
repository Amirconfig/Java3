package ca.sheridancollege.khanmoam.controllers;

import ca.sheridancollege.khanmoam.beans.Book;
import ca.sheridancollege.khanmoam.beans.Review;
import ca.sheridancollege.khanmoam.database.DatabaseAccess;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller class for handling review-related operations.
 */
@Controller
public class ReviewController {

	private final DatabaseAccess databaseAccess;

	/**
	 * Constructs a new ReviewController with the specified DatabaseAccess.
	 * 
	 * @param databaseAccess The database access object.
	 */
	public ReviewController(DatabaseAccess databaseAccess) {
		this.databaseAccess = databaseAccess;
	}

	/**
	 * Displays the form for viewing reviews of a book.
	 * 
	 * @param bookId The ID of the book for which reviews are to be displayed.
	 * @param model  The model to be used for rendering the view.
	 * @return The view name for displaying the book and its reviews.
	 */
	@GetMapping("/view/review")
	public String reviewForm(@RequestParam("bookId") long bookId, Model model) {
		Book book = databaseAccess.getBookById(bookId);
		model.addAttribute("book", book);
		return "view-book";
	}

	/**
	 * Displays the form for adding a new review for a book.
	 * 
	 * @param bookId The ID of the book for which a review is being added.
	 * @param model  The model to be used for rendering the view.
	 * @return The view name for displaying the add-review form.
	 */
	@GetMapping("/add/review")
	public String addReview(@RequestParam("bookId") long bookId, Model model) {
		Book book = databaseAccess.getBookById(bookId);
		Review reviewForm = new Review();
		model.addAttribute("reviewForm", reviewForm);
		model.addAttribute("book", book);
		return "user/add-review";
	}

	/**
	 * Handles the submission of the add-review form. Adds the review to the
	 * database and redirects to the index page.
	 * 
	 * @param bookId        The ID of the book for which the review is being added.
	 * @param reviewForm    The Review object submitted through the form.
	 * @param bindingResult The result of the binding process.
	 * @param model         The model to be used for rendering the view.
	 * @return The redirect URL to the index page.
	 */
	@PostMapping("/add/review")
	public String addReviewPost(@RequestParam("bId") long bookId, @ModelAttribute("reviewForm") Review reviewForm,
			BindingResult bindingResult, Model model) {
		reviewForm.setBookId(bookId);
		databaseAccess.addReview(reviewForm);
		return "redirect:/index.html";
	}
}
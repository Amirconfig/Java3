package ca.sheridancollege.khanmoam.beans;

import lombok.EqualsAndHashCode;

/**
 * Represents a review for a book. Each review has an ID, book ID (identifying
 * the book it belongs to), and text content.
 */
@EqualsAndHashCode
public class Review {

	private long id;
	private long bookId;
	private String text;

	/**
	 * Getter for ID
	 * 
	 */
	public long getId() {
		return id;
	}

	/**
	 * Setter for ID
	 * 
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the book ID associated with the review.
	 * 
	 * @return The book ID associated with the review.
	 */
	public long getBookId() {
		return bookId;
	}

	/**
	 * Sets the book ID associated with the review.
	 * 
	 * @param bookId The book ID to set for the review.
	 */
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	/**
	 * Gets the text content of the review.
	 * 
	 * @return The text content of the review.
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text content of the review.
	 * 
	 * @param text The text content to set for the review.
	 */
	public void setText(String text) {
		this.text = text;
	}
}
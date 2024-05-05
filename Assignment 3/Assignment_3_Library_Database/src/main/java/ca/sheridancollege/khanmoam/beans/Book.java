package ca.sheridancollege.khanmoam.beans;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;

/**
 * Represents a book entity. Each book has an ID, title, author, and a
 * list of reviews.
 */
@EqualsAndHashCode
public class Book {

	private long id;
	private String title;
	private String author;
	private List<Review> reviews;

	/**
	 * Default constructor for the Book class. Initializes the list of reviews as an
	 * empty ArrayList.
	 */
	public Book() {
		reviews = new ArrayList<>();
	}

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
	 * Getter for title
	 * 
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setter for title
	 * 
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Getter for author
	 * 
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Setter for author
	 * 
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Getter for revies
	 * 
	 */
	public List<Review> getReviews() {
		return reviews;
	}

	/**
	 * Setter for author
	 * 

	 */
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
}
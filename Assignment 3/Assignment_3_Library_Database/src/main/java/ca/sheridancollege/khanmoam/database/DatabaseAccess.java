package ca.sheridancollege.khanmoam.database;

import ca.sheridancollege.khanmoam.beans.Book;
import ca.sheridancollege.khanmoam.beans.Review;
import ca.sheridancollege.khanmoam.beans.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Database access class responsible for interacting with the underlying
 * database.
 */
@Component
public class DatabaseAccess {

	private final JdbcTemplate jdbcTemplate;
	private final BCryptPasswordEncoder passwordEncoder;

	/**
	 * Constructs a new DatabaseAccess with the specified JdbcTemplate.
	 *
	 * @param jdbcTemplate The JdbcTemplate to be used for database access.
	 */
	public DatabaseAccess(JdbcTemplate jdbcTemplate, BCryptPasswordEncoder passwordEncoder) {
		this.jdbcTemplate = jdbcTemplate;
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * Retrieves a list of all books from the database.
	 *
	 * @return The list of all books.
	 */
	public List<Book> getAllBooks() {
		String query = "SELECT * FROM books";
		return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Book.class));
	}

	/**
	 * Adds a new book to the database.
	 *
	 * @param book The Book object representing the book to be added.
	 */
	public void addBook(Book book) {
		String query = "INSERT INTO books (title, author) VALUES (?, ?)";
		jdbcTemplate.update(query, book.getTitle(), book.getAuthor());
	}

	/**
	 * Retrieves a book by its ID along with its associated reviews.
	 *
	 * @param id The ID of the book to retrieve.
	 * @return The Book object with associated reviews.
	 */
	public Book getBookById(long id) {
		String query = "SELECT b.id, b.title, b.author, r.id as review_id, r.text as review_text "
				+ "FROM books b LEFT JOIN reviews r ON b.id = r.bookId WHERE b.id = ?";

		return jdbcTemplate.query(query, new BookWithReviewsExtractor(), id);
	}

	/**
	 * Adds a new review to the database.
	 *
	 * @param review The Review object representing the review to be added.
	 */
	public void addReview(Review review) {
		String query = "INSERT INTO reviews (bookId, text) VALUES (?, ?)";
		jdbcTemplate.update(query, review.getBookId(), review.getText());
	}

	/**
	 * Checks if a user with the given username and password exists in the database.
	 *
	 * @param username The username of the user.
	 * @param password The password of the user.
	 * @return true if the user exists, false otherwise.
	 */
	public boolean userExists(String username, String password) {
		String query = "SELECT COUNT(*) FROM users WHERE userName = ? AND password = ?";
		int count = jdbcTemplate.queryForObject(query, Integer.class, username, passwordEncoder.encode(password));
		return count > 0;
	}

	/**
	 * Adds a new user to the database.
	 *
	 * @param user The User object representing the user to be added.
	 */
	public void addUser(User user) {
		String query = "INSERT INTO users (role, userName, password) VALUES (?, ?, ?)";
		jdbcTemplate.update(query, user.getRole().name(), user.getUserName(),
				passwordEncoder.encode(user.getPassword()));
	}

	/**
	 * Checks if a user with the given username exists in the database.
	 *
	 * @param username The username of the user.
	 * @return true if the user exists, false otherwise.
	 */
	public boolean userExists(String username) {
		String query = "SELECT COUNT(*) FROM users WHERE userName = ?";
		int count = jdbcTemplate.queryForObject(query, Integer.class, username);
		return count > 0;
	}

	/**
	 * Retrieves a user by their username from the database.
	 *
	 * @param username The username of the user to retrieve.
	 * @return The User object representing the retrieved user.
	 */
	public User getUserByUsername(String username) {
		String query = "SELECT * FROM users WHERE userName = ?";
		return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(User.class), username);
	}

	/**
	 * Nested class for extracting Book objects with associated reviews from a
	 * ResultSet.
	 */
	private static class BookWithReviewsExtractor implements ResultSetExtractor<Book> {
		@Override
		public Book extractData(ResultSet resultSet) throws SQLException, DataAccessException {
			Map<Long, Book> bookMap = new HashMap<>();
			Book book = null;

			while (resultSet.next()) {
				long bookId = resultSet.getLong("id");

				if (!bookMap.containsKey(bookId)) {
					book = new Book();
					book.setId(bookId);
					book.setTitle(resultSet.getString("title"));
					book.setAuthor(resultSet.getString("author"));
					bookMap.put(bookId, book);
				}

				long reviewId = resultSet.getLong("review_id");
				if (reviewId > 0) {
					Review review = new Review();
					review.setId(reviewId);
					review.setBookId(bookId);
					review.setText(resultSet.getString("review_text"));
					book.getReviews().add(review);
				}
			}

			return book;
		}
	}
}
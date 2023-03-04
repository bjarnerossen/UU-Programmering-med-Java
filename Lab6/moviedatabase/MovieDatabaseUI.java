package moviedatabase;

import java.util.Scanner;

/**
 * A command line user interface for a movie database.
 */
public class MovieDatabaseUI {
	private final Scanner scanner;
	private final MovieDatabase database;
	/**
	 * Construct a MovieDatabaseUI.
	 */
	public MovieDatabaseUI() {
		scanner = new Scanner(System.in);
		database = new MovieDatabase();
	}
	/**
	 * Start the movie database UI.
	 */
	public void startUI() {
		System.out.println("** MOVIE DATABASE **");

		try {
			while (true) {
				int menuChoice = getNumberInput(scanner, 1, 4, getMainMenu());

				switch (menuChoice) {
					case 1 -> searchTitle();
					case 2 -> searchReviewScore();
					case 3 -> addMovie();
					case 4 -> {
						System.out.println("Exiting program...");
						return;
					}
					default -> throw new IllegalStateException("Unexpected value: " + menuChoice);
				}
			}
		} catch (Exception e) {
			System.err.println("An error occurred: " + e.getMessage());
		}
	}

	/**
	 * Get input and translate it to a valid number.
	 *
	 * @param scanner the Scanner we use to get input
	 * @param min the lowest correct number
	 * @param max the highest correct number
	 * @param message message to user
	 * @return the chosen menu number
	 */
	private int getNumberInput(Scanner scanner, int min, int max, String message) {
		int input;
		do {
			System.out.println(message);
			try {
				input = Integer.parseInt(scanner.nextLine().trim());
			} catch (NumberFormatException nfe) {
				System.out.println("Invalid input. Please enter a number.");
				input = -1;
			}
			if (input < min || input > max) {
				System.out.println("Invalid value. Please enter a number from " + min + " to " + max + ".");
				input = -1;
			}
		} while (input < 0);
		return input;
	}

	/**
	 * Get search string from user, search title in the movie
	 * database and present the search result.
	 */
	private void searchTitle() {
		System.out.print("Enter key word: ");
		String title = scanner.nextLine().trim();

		Movie[] result = database.searchByTitle(title);

		if (result.length == 0) {
			System.out.println("No results found.");
		} else {
			System.out.println("Results:");
			for (Movie m : result) {
				System.out.println("Title: " + m.getTitle() + " (Review Score: " + m.getReviewScore() + "/5)");
			}
		}
	}
	/**
	 * Get search string from user, search review score in the movie
	 * database and present the search result.
	 */
	private void searchReviewScore() {
		int review = getNumberInput(scanner, 1, 5, "Enter minimum review score (1 - 5): ");

		Movie[] result = database.searchByReviewScore(review);

		if (result.length == 0) {
			System.out.println("No results found.");
		} else {
			System.out.println("Results:");
			for (Movie m : result) {
				System.out.println("Title: " + m.getTitle() + " (Review Score: " + m.getReviewScore() + "/5)");
			}
		}
	}
	/**
	 * Get information from user on the new movie and add
	 * it to the database.
	 */
	private void addMovie() {
		String title = null;
		while (title == null || title.trim().isEmpty()) {
			System.out.print("Title: ");
			title = scanner.nextLine().trim();
			if (title.trim().isEmpty()) {
				System.out.println("Please enter a title.");
			}
		}

		int reviewScore = getNumberInput(scanner, 1, 5, "Review score (1 - 5): ");
		Movie movie = new Movie(title, reviewScore);

		System.out.println("You are about to add the following movie: ");
		System.out.println("Title: " + movie.getTitle() + "\n" + "Review Score: " + movie.getReviewScore());
		System.out.print("Do you want to add this movie? (Y/N): ");
		String confirmation = scanner.nextLine().trim().toLowerCase();
		if (confirmation.equals("y")) {
			try {
				database.addMovie(movie);
				System.out.println(movie.getTitle() + " added successfully.");
			} catch (Exception e) {
				System.out.print("Failed to add movie to database. Error: " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			System.out.println("Movie was not added.");
		}
	}

	/**
	 * Returns the main menu text as a formatted String.
	 * The menu items are stored in an array and formatted using a StringBuilder.
	 * The menu items are numbered starting from 1.
	 * If there are 3 or more menu items, a separating line is added after the 3rd item.
	 * @return the main menu text as a formatted String
	 */
	private String getMainMenu() {
		String[] menuItems = {
				"Search title",
				"Search review score",
				"Add movie",
				"Close program"
		};
		StringBuilder sb = new StringBuilder();
		sb.append("-------------------\n");
		for (int i = 0; i < menuItems.length; i++) {
			sb.append(String.format("%d. %s%n", i+1, menuItems[i]));
			if (i == (menuItems.length-2)) {
				sb.append("-------------------\n");
			}
		}
		return sb.toString().trim();
	}
}






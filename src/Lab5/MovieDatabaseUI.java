package lab5;

import java.util.Scanner;
import java.util.List;
/**
 * A command line user interface for a movie database.
 */
public class MovieDatabaseUI {
	private Scanner _scanner;
	private Database _movieDb; 
	
	/**
	 * Construct a MovieDatabaseUI.
	 * @param movieDb		object of Database interface
	 */
	public MovieDatabaseUI(Database movieDb) {
		this._movieDb = movieDb;
	}

	/**
	 * Start the movie database UI.
	 */
	public void startUI() {
		_scanner = new Scanner(System.in);
		int input;		
		boolean quit = false;

		System.out.println("** FILMDATABAS **");

		while(!quit) {			
			input = getNumberInput(_scanner, 1, 4, getMainMenu());

			switch(input) {
			case 1: searchTitle(); break;
			case 2: searchReviewScore(); break;
			case 3: addMovie(); break;
			case 4: quit = true; 			
			}
		}
		//Close scanner to free resources
		_scanner.close();
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
		int input = -1;

		while(input < 0) {
			System.out.println(message);
			try {
				input = Integer.parseInt(scanner.nextLine().trim());		
			} 
			catch(NumberFormatException nfe) {
				input = -1;
			}
			if(input < min || input > max) {
				System.out.println("Ogiltigt värde.");
				input = -1;
			}			
		}			
		return input;
	}

	/**
	 * Get search string from user, search title in the movie 
	 * database and present the search result.
	 */
	private void searchTitle() {
		System.out.print("Ange sökord: ");
		String titleQuery = _scanner.nextLine().trim();
		List<List<String>> moviesFound = this._movieDb.searchTitle(titleQuery);
		this.printMovies(moviesFound);	
	}

	/**
	 * Get search string from user, search review score in the movie 
	 * database and present the search result.
	 */
	private void searchReviewScore() {		
		int reviewQuery = getNumberInput(_scanner, 1, 5, "Ange minimibetyg (1 - 5): ");
		List<List<String>> moviesFound = this._movieDb.searchRating(reviewQuery);
		this.printMovies(moviesFound);	
	}

	/**
	 * Get information from user on the new movie and add
	 * it to the database.
	 */
	private void addMovie() {
		System.out.print("Titel: ");
		String title = _scanner.nextLine().trim();
		int reviewScore = getNumberInput(_scanner, 1, 5, "Betyg (1 - 5): ");
		_movieDb.addMovie(title, reviewScore);
	}

	/**
	 * Return the main menu text.
	 * 
	 * @return the main menu text
	 */
	private String getMainMenu() {
		return  "-------------------\n" +
				"1. Sök på titel\n" +
				"2. Sök på betyg\n" +	
				"3. Lägg till film\n" +
				"-------------------\n" + 
				"4. Avsluta";
	}

	/**
	 * Formats and prints out title and rating of a list of movies.
	 * 
	 * @param movies		list of movies to print
	*/
	private void printMovies(List<List<String>> movies) {
		if (movies.size() > 0) {
			for (List<String> movieInfo : movies) {
				String title = movieInfo.get(0);
				String rating = movieInfo.get(1);
				System.out.println(String.format("Titel: %s Betyg: %s", title, rating));
			}
		}
		else {
			System.out.println("Inga filmer hittades.");
		}
	}
}

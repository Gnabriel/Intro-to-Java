package lab5;

/**
 * Entry point for a movie database as part of an assignment 
 * in the course Introduction to Programming with Java.
 */
public class Lab5 {
	/**
	 * Program entry point. Starts the movie database UI.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		// Construct the database at specifield file path.
		MovieDatabase movieDb = new MovieDatabase("./src/lab5/movie_db.txt");
		// Construct and start the UI.
		new MovieDatabaseUI(movieDb).startUI();
	}
}
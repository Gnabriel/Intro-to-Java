package lab5;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

/**
* A database interface.
*/
interface Database {
    public void addMovie(String title, int rating);
    public List<List<String>> searchTitle(String title);
    public List<List<String>> searchRating(int rating);
}

/**
* A database to store and query movies.
*/
public class MovieDatabase implements Database {
    private Path _databasePath;
    /**
    * Construct a MovieDatabase.
    * @param filePath		path to the database file
    */
    public MovieDatabase(String filePath) {
        this._databasePath = createFile(filePath);
    }

    /**
    * Creates a file at a specified path.
    * 
    * @param filePath		path where to create the file
    * @return 				Path object to the file
    */
    private Path createFile(String filePath) {
        Path path = Paths.get(filePath);
        try {
            // Create file if it does not already exist.
            if(!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    /**
    * Reads all content of the database file.
    * 
    * @return       List with each line in the database
    */
    private List<String> readDatabase() {
        List<String> databaseContent = new ArrayList<>();
        try {
            databaseContent = Files.readAllLines(this._databasePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return databaseContent;
    }

    /**
    * Adds a movie to the database file.
    * Each line represents one movie, formatted as:
    * "title,rating"
    * 
    * @param title			title of the movie
    * @param reviewScore	rating of the movie
    */
    public void addMovie(String title, int rating) {
        // Create string with title and score.
        String movieInfo = String.format("%s,%d%n", title, rating);
        try {
            // Append string to database file.
            Files.write(this._databasePath, movieInfo.getBytes(), StandardOpenOption.APPEND);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    * Searches the database for a string title query.
    * 
    * @param titleQuery		title to search for
    * @return               list of movies found by the query
    */
    public List<List<String>> searchTitle(String titleQuery) {
        List<String> databaseContent = this.readDatabase();
        List<String> movieInfo = new ArrayList<>();
        List<List<String>> moviesFound = new ArrayList<>();
        for (String movie : databaseContent) {
            // Check if not end of the file.
            if (!movie.isEmpty()) {
                movieInfo = Arrays.asList(movie.split(","));
                String title = movieInfo.get(0);
                // Search non-case sensitive.
                if (title.toLowerCase().contains(titleQuery.toLowerCase())) {
                    moviesFound.add(movieInfo);
                }
            }
        }
        // Returns empty array if query not found.
        return moviesFound;
    }

    /**
    * Searches the database for an int rating query.
    * 
    * @param ratingQuery    rating to search for
    * @return               list of movies found by the query
    */
    public List<List<String>> searchRating(int ratingQuery) {
        List<String> databaseContent = this.readDatabase();
        List<String> movieInfo = new ArrayList<>();
        List<List<String>> moviesFound = new ArrayList<>();
        for (String movie : databaseContent) {
            // Check if not end of the file.
            if (!movie.isEmpty()) {
                movieInfo = Arrays.asList(movie.split(","));
                int rating = Integer.parseInt(movieInfo.get(1));
                if (rating >= ratingQuery) {
                    moviesFound.add(movieInfo);
                }
            }
        }
        // Returns empty array if query not found.
        return moviesFound;
    }
}

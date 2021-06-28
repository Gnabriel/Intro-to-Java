package lab5;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.StandardOpenOption;

interface Database {
    public void addMovie(String title, int rating);
    public String searchTitle(String title);
    public String searchRating(int rating);
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
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    private void readFile() {
        
    }

    /**
    * Adds a movie to the database file.
    * 
    * @param title			title of the movie
    * @param reviewScore	rating of the movie
    */
    public void addMovie(String title, int reviewScore) {
        // Create string with title and score.
        String movieInfo = String.format("%s, %d%n", title, reviewScore);
        try {
            // Append string to database file.
            Files.write(this._databasePath, movieInfo.getBytes(), StandardOpenOption.APPEND);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String searchTitle(String title) {
        return ".";
    }

    public String searchRating(int rating) {
        return ".";
    }
}

package moviedatabase;

import java.io.*;
import java.util.*;

/**
 * A movie database that stores movies in a file in CSV format.
 */
public class MovieDatabase {
    private final String fileName;
    private final List<Movie> movies;

    /**
     * Construct a new MovieDatabase.
     */
    public MovieDatabase() {
        String className = this.getClass().getSimpleName();
        fileName = className + ".csv";

        movies = new ArrayList<>();
        loadMoviesFromFile();
    }

    /**
     * Add a movie to the database.
     *
     * @param movie the movie to add
     */
    public void addMovie(Movie movie) {
        movies.add(movie);
        saveMoviesToFile();
    }

    /**
     * Search for movies by title.
     *
     * @param title the title to search for
     * @return an array of matching movies
     */
    public Movie[] searchByTitle(String title) {
        List<Movie> matchingMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(title.toLowerCase())) {
                matchingMovies.add(movie);
            }
        }
        return matchingMovies.toArray(new Movie[0]);
    }

    /**
     * Searches for movies with a review score greater than or equal to the specified score.
     * @param score the minimum review score to search for
     * @return an array of matching movies, sorted in descending order of review score
     */
    public Movie[] searchByReviewScore(int score) {
        List<Movie> matchingMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getReviewScore() >= score) {
                matchingMovies.add(movie);
            }
        }
        matchingMovies.sort(Comparator.comparing(Movie::getReviewScore).reversed());
        return matchingMovies.toArray(new Movie[0]);
    }

    /**
     * Load movies from file into the database.
     */
    private void loadMoviesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String title = parts[0].trim();
                int score = Integer.parseInt(parts[1].trim());
                movies.add(new Movie(title, score));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Movie database file not found.");
        } catch (IOException e) {
            System.out.println("Error reading movie database file.");
        } catch (NumberFormatException e) {
            System.out.println("Error parsing movie review score.");
        }
    }

    /**
     * Save movies from the database to file.
     */
    private void saveMoviesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Movie movie : movies) {
                writer.write(movie.getTitle() + "," + movie.getReviewScore() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing movie database file.");
        }
    }
}

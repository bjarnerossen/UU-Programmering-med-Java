package moviedatabase;

import java.io.Serial;
import java.io.Serializable;

/**
 * The Movie class represents a movie, which has a title and a review score.
 * This class implements the Serializable interface so that instances of this
 * class can be serialized and deserialized.
 */

public record Movie(String title, int reviewScore) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a Movie object with the specified title and review score.
     *
     * @param title        the title of the movie
     * @param reviewScore  the review score of the movie
     */
    public Movie(String title, int reviewScore) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }

        if (reviewScore < 0 || reviewScore > 10) {
            throw new IllegalArgumentException("Review score should be between 0 and 10");
        }

        this.title = title.trim();
        this.reviewScore = reviewScore;
    }

    /**
     * Returns the title of the movie.
     *
     * @return the title of the movie
     */
    public String getTitle() {
        return title;
    }

    /**
     Returns the review score of the movie.
     @return the review score of the movie
     */
    public int getReviewScore() {
        return reviewScore;
    }

    /**
     Returns a string representation of the Movie object in the form "title,score".
     @return a string representation of the Movie object
     */
    @Override
    public String toString() {
        return title + "," + reviewScore;
    }
}

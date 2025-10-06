import java.util.Objects;

/*
 * Book represents a single record from the input file.
 * It is an immutable data holder: title, author, genre, and yearPublished.
 * Construction trims text fields; validation is performed by the loader (Library).
 * toString() returns a clean, one-line display: "Title — Author — Genre — Year".
 */
public class Books {
    // --- Fields are private and final to keep each Book immutable ---
    private final String title;         // non-empty after trimming
    private final String author;        // may contain spaces/punctuation
    private final String genre;         // e.g., "Fantasy"; used for filtering (case-insensitive)
    private final int yearPublished;    // validated integer year

    /*
     * Constructor: trims nulls to empty strings (defensive),
     * but note: loader enforces non-empty strings and valid year before creating.
     */
    public Books(String title, String author, String genre, int yearPublished) {
        this.title = title == null ? "" : title.trim();
        this.author = author == null ? "" : author.trim();
        this.genre = genre == null ? "" : genre.trim();
        this.yearPublished = yearPublished;
    }

    // --- Simple getters (read-only accessors) ---
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public int getYearPublished() { return yearPublished; }

    /*
     * Single-line, human-friendly representation used when listing books.
     * (Keeps console output clean and consistent.)
     */
    @Override
    public String toString() {
        return String.format("%s - %s - %s - %d", title, author, genre, yearPublished);
    }

    // Equality/hashCode let us compare books or put them in sets/maps if needed.
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Books)) return false;
        Books other = (Books) o;
        return yearPublished == other.yearPublished
                && Objects.equals(title, other.title)
                && Objects.equals(author, other.author)
                && Objects.equals(genre, other.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, genre, yearPublished);
    }
}

import java.time.LocalDate;

public class Book {

    private final String title;
    private final String author;
    private final Genre genre;
    private final int yearPublished;

    public enum Genre {
        FICTION,
        FANTASY,
        CLASSIC,
        MYSTERY,
        HORROR,
        ROMANCE,
        BIOGRAPHY,
        HISTORY,
        POETRY,
        CHILDREN,
        SCIENCE
    }

    public Book(String title, String author, Genre genre, int yearPublished) throws Exception {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("Title can't be blank");
        if(!isValidName(author))
            throw new IllegalArgumentException("Author can't be blank or have invalid characters like numbers or symbols");
        if (genre == null)
            throw new IllegalArgumentException("Genre can't be null");
        if (!isValidYearPublished(yearPublished))
            throw new IllegalArgumentException("Invalid year");

        this.title = title.trim();
        this.author = author.trim();
        this.genre = genre;
        this.yearPublished = yearPublished;
    }

    private boolean isValidName(String name) {
        return name != null && !name.isBlank() && name.matches("^[A-Za-z .]+$");
    }

    private boolean isValidYearPublished(int yearPublished) {
        return (yearPublished > 0 && yearPublished <= (LocalDate.now().getYear()));
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    @Override
    public String toString() {
        String formattedGenre = genre.name().charAt(0) + genre.name().substring(1).toLowerCase();
        return "Title: " + title + ", Author: " + author + ", Genre: " + formattedGenre + ", Year: " + yearPublished;
    }
}

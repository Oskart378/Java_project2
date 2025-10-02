import java.time.LocalDate;

public class Book {

    private String title;
    private String author;
    private Genre genre;
    private int yearPublished;

    public enum Genre {
        FICTION,
        FANTASY,
        SCIENCE_FICTION,
        MYSTERY,
        HORROR,
        ROMANCE,
        BIOGRAPHY,
        HISTORY,
        POETRY,
        CHILDREN,
        SCIENCE
    }

    public Book(String title, String authorFirst, String authorLast, Genre genre, int yearPublished) throws Exception {
        setAuthor(authorFirst, authorLast);
        setGenre(genre);
        setTitle(title);
        setYearPublished(yearPublished);
    }

    private boolean isValidName(String name) {
        return name != null && !name.isBlank() && name.matches("^[A-Za-z](?:[A-Za-z'\\-]*[A-Za-z])?(?: [A-Za-z'\\-]+)*$");
    }

    private void setAuthor(String authorFirst, String authorLast) throws Exception {
        if (isValidName(authorFirst) && isValidName(authorLast))
            this.author = authorFirst + " " + authorLast;
        else
            throw new Exception("Author can't be blank");

    }

    private void setYearPublished(int yearPublished) throws Exception {
        if (yearPublished > 0 && yearPublished <= (LocalDate.now().getYear()))
            this.yearPublished = yearPublished;
        else
            throw new Exception("Invalid year. must be a year between 0 and " +
                    LocalDate.now().getYear());
    }

    private void setGenre(Genre genre) throws Exception {
        this.genre = genre;
    }


    private void setTitle(String title) throws Exception {
        if (title != null && !title.isBlank())
            this.title = title;
        else
            throw new Exception("Title can't be blank");
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
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre=" + genre.toString().replace("_", " ").toLowerCase() +
                ", year published=" + yearPublished +
                '}';
    }
}

import java.time.LocalDate;

public class Book {

    private String title;
    private String author;
    private Genre genre;
    private int yearPublished;

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
        setAuthor(author);
        setGenre(genre);
        setTitle(title);
        setYearPublished(yearPublished);
    }

    private boolean isValidName(String name) {
        return name != null && !name.isBlank() && name.matches("^[A-Za-z .]+$");
    }

    private void setAuthor(String author) throws Exception {
        if (isValidName(author))
            this.author = author;
        else
            throw new Exception("Author can't be blank or have invalid characters like numbers or symbols");

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
                ", genre=" + genre.toString().toLowerCase() +
                ", year published=" + yearPublished +
                '}';
    }
}

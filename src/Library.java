import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    private final ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public Library(String fileName) {
        this();
        loadBooksFromFile(fileName);
    }

    public void loadBooksFromFile(String fileName) {

        books.clear();
        fileName = fileName.trim();
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("Sorry, could not load books, file does not exist");
            return;
        }

        try (Scanner fileReader = new Scanner(file)) {

            int lineNum = 0;
            while(fileReader.hasNext()) {

                lineNum++;
                String bookRow = fileReader.nextLine();
                String[] bookInfo = bookRow.split(";");

                if (bookRow.isBlank())
                    continue;

                if (bookInfo.length != 4) {
                    System.out.println("Book not added, row does not have enough data");
                    continue;
                }

               try {
                    String title = bookInfo[0].trim();
                    String author = bookInfo[1].trim();
                    Book.Genre genre = Book.Genre.valueOf(bookInfo[2].trim().toUpperCase());
                    int year = Integer.parseInt(bookInfo[3].trim());

                    books.add(new Book(title, author, genre, year));
               }

               catch (Exception ex) {
                    System.out.println("Error in line #" + lineNum + ": " + ex.getMessage());
                }
            }
        }

        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }

    public void displayAllBooks() {

        if (books.isEmpty()) {
            System.out.println("No books loaded books in library, try to load books first");
            return;
        }

        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i));
        }
    }

    public void searchByTitle(String title) {

        if (books.isEmpty()) {
            System.out.println("No books loaded in library, try to load books first");
            return;
        }

        var filtered = books.stream().filter(b -> b.getTitle().toLowerCase().
                startsWith(title.trim().toLowerCase())).toList();

        if (filtered.isEmpty())
            System.out.println("There is no book with title of " + title.toLowerCase()
                    + " yet");
        else
            filtered.forEach(System.out::println);
    }

    public void filterByGenre(Book.Genre genre) {
        if (books.isEmpty()) {
            System.out.println("No books loaded in library, try to load books first");
            return;
        }

        var filtered = books.stream().filter(b ->b.getGenre() == genre).toList();

        if (filtered.isEmpty())
            System.out.println("There is no books of the genre " + genre.toString().toLowerCase()
                    + " yet");
        else
            filtered.forEach(System.out::println);
    }
}

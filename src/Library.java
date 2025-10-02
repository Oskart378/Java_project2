import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    private final ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void loadBooksFromFile(String fileName) {

        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("Sorry, could not load books, file does not exist");
            return;
        }

        try (Scanner fileReader = new Scanner(file)) {

            while(fileReader.hasNext()) {
                int lineNum = 1;
                String bookRow = fileReader.nextLine();
                String[] bookInfo = bookRow.split(";");

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
                    System.out.println("Error in line #" + lineNum + "\n" + ex.getMessage());
                }
            }
        }

        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }

    public void displayAllBooks() {

        if (books.isEmpty()) {
            System.out.println("Not loaded books in library, try to load books first");
            return;
        }

        books.forEach(System.out::println);
    }

    public void searchByTitle(String title) {

        if (books.isEmpty()) {
            System.out.println("Not loaded books in library, try to load books first");
            return;
        }

        var filtered = books.stream().filter(b -> b.getTitle().startsWith(title)).toList();

        if (filtered.isEmpty())
            System.out.println("There is no book with title of " + title.toString().toLowerCase()
                    + " yet");
        else
            filtered.forEach(System.out::println);
    }

    public void filterByGenre(Book.Genre genre) {
        if (books.isEmpty()) {
            System.out.println("Not loaded books in library, try to load books first");
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

import java.io.File;
import java.io.IOException;
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

        /*if (!file.exists()) {
            System.out.println("Sorry, could not load books, file does not exist");
            return;
        }*/

        try (Scanner fileReader = new Scanner(file)) {
            int corruptDataNum = 0;
            while(fileReader.hasNext()) {

                String bookRow = fileReader.nextLine();
                String[] bookInfo = bookRow.split(";");

                if (bookRow.isBlank())
                    continue;

                if (bookInfo.length != 4) {
                    corruptDataNum++;
                    continue;
                }

               try {
                    String title = bookInfo[0].trim();
                    String author = bookInfo[1].trim();
                    String genre = bookInfo[2].trim();
                    int year = Integer.parseInt(bookInfo[3].trim());

                    books.add(new Book(title, author, genre, year));


               }

               catch (IOException ex) {
                   System.out.println("Error: could not open '" + fileName + "'. Please check the path and permission try again.");
               }

               catch (Exception ex) {
                    //System.out.println("Error: " + ex.getMessage());
                    corruptDataNum++;
                }
            }

            System.out.println("Loaded " + books.size() + " book(s).");

            if(corruptDataNum > 0)
                System.out.println("Skipped " + corruptDataNum + " corrupted line(s).");
        }

        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }

    public void displayAllBooks() {

        if (books.isEmpty()) {
            System.out.println("No books loaded in library, try to load books first");
            return;
        }

        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i));
        }

        System.out.println("\nTotal books: " + books.size());
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
        else {
            filtered.forEach(System.out::println);
            System.out.println("\nMatches found: " + filtered.size());
        }

    }

    public void filterByGenre(String genre) {
        if (books.isEmpty()) {
            System.out.println("No books loaded in library, try to load books first");
            return;
        }

        var filtered = books.stream().filter(b -> b.getGenre().equalsIgnoreCase(genre)).toList();

        if (filtered.isEmpty())
            System.out.println("There is no books of the genre " + genre.toLowerCase()
                    + " yet");
        else {
            filtered.forEach(System.out::println);
            System.out.println("\nMatches found: " + filtered.size());
        }
    }


    public boolean isLibraryEmpty() {
        return books.isEmpty();
    }
}

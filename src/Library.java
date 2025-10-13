import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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

        if (!file.exists() || !file.canRead()) {
            System.out.println("Sorry, could not load books, file does not exist or check permissions");
            return;
        }

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
                    books.add(new Book(bookInfo[0], bookInfo[1], bookInfo[2], Integer.parseInt(bookInfo[3].trim())));
               }

               catch (Exception ex) {
                    corruptDataNum++;
                }
            }

            System.out.println("Loaded " + books.size() + " book(s).");

            if(corruptDataNum > 0)
                System.out.println("Skipped " + corruptDataNum + " corrupted line(s).");
        }

        catch (FileNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void displayAllBooks() {

        if (books.isEmpty()) {
            System.out.println("No books loaded in library, try to load books first");
            return;
        }

        for (Book book : books) {
            System.out.println(book);
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


    public TreeSet<String> getAvailableGenres() {
        TreeSet<String> availableGenres = new TreeSet<>();

        try {

            books.forEach(b -> {
                String g = b.getGenre();
                if (!g.isBlank()) {
                    availableGenres.add(g.substring(0,1).toUpperCase() + g.substring(1).toLowerCase());
                }
            });
        }

        catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return availableGenres;
    }

    public boolean isLibraryEmpty() {
        return books.isEmpty();
    }
}

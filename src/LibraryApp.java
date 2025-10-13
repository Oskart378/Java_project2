import java.io.File;
import java.util.Scanner;

public class LibraryApp {

    private static final Scanner input = new Scanner(System.in);
    private final Library library;

    public LibraryApp(Library library) {
        this.library = library;
    }

    public static void main(String[] args) throws Exception {
        Library library = new Library();
        new LibraryApp(library).run();
    }

    private static void pauseConsole() {
        System.out.println("Press enter to go back to main menu...");

        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.isEmpty()) break; // stop only when the user really just hits enter
        }
    }

    public void run() throws Exception {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = promptMenuChoice();

            switch (choice) {
                case 1 -> loadBooks();
                case 2 -> displayAllBooks();
                case 3 -> searchByTitle();
                case 4 -> filterByGenre();
                case 5 -> { System.out.println("Good Bye!"); running = false;}
                default -> System.out.println("Please enter a valid choice between 1 and 5.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n===== Library Menu =====");
        System.out.println("1. Load books from file");
        System.out.println("2. Display all books.");
        System.out.println("3. Search by title.");
        System.out.println("4. Filter by genre.");
        System.out.println("5. Exit");
        System.out.print("Enter an option: ");
    }

    private int promptMenuChoice() {
        while (true){
            String inputStr = input.nextLine().trim();
            if (inputStr.isEmpty()) {
                System.out.println("Please enter a valid choice between 1 and 5.");
                //printMenu();
                continue;
            }

            try {
                int choice = Integer.parseInt(inputStr);
                if (choice >= 1 && choice <= 5)
                    return choice;
                else
                    System.out.println("Please enter a valid choice between 1 and 5.");

            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid choice between 1 and 5.");
            }

        }
    }

    private void loadBooks() {
        clearScreen();
        String newPath = promptValidFileName();
        library.loadBooksFromFile(newPath);
        pauseConsole();
    }

    private void displayAllBooks() {
        clearScreen();
        library.displayAllBooks();
        pauseConsole();
    }

    private void searchByTitle() {
        clearScreen();
        if (library.isLibraryEmpty()) {
            System.out.println("No books loaded in the library. please try to load books first.");
        }

        else {
            System.out.print("Enter a book title: ");
            String title = input.nextLine().trim();
            library.searchByTitle(title);
        }
        pauseConsole();

    }

    private void filterByGenre() {
        clearScreen();
        if (library.isLibraryEmpty()) {
            System.out.println("No books loaded in the library. please try to load books first.");
        }

        else {
            System.out.println("Available genres:");
            System.out.println(library.getAvailableGenres().toString());
            System.out.print("Enter a genre: ");
            String genre = input.nextLine().trim(); /*promptValidGenre();*/
            library.filterByGenre(genre);
        }
        pauseConsole();
    }


    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static String promptValidFileName() {

        while (true){
            System.out.print("Enter the file path: ");
            String path = input.nextLine().trim();
            File file = new File(path);

            if (!file.exists())
                System.out.println("Error: file does not exist. Try again");
            else if (!file.isFile())
                System.out.println("Not a regular file. Try again.");
            else if (!file.canRead())
                System.out.println("Error: file can't be read. Check permissions and try again.");
            else {
                System.out.println("File " + file.getName() + " is a valid file. loading books....");
                return path;
            }

        }

    }

    /*public static String promptValidGenre() {

        while (true){
            String genreInput;
            System.out.println("Enter a genre: ");
            genreInput = input.nextLine().toUpperCase().trim();

            try {
                return Book.Genre.valueOf(genreInput);
            } catch (IllegalArgumentException ex) {
                System.out.println("Not a valid genre!");
            }
        }*/
    }


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LibraryApp {

    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        libraryMenu();

    }

    public static void libraryMenu() {

        System.out.println("Enter file name or path to load the books: ");
        String booksFilePath = promptValidFileName();
        Library library = new Library(booksFilePath);

        outerLoop:
        do {
            int choice;
            String choiceStr;
            System.out.println("1. Reload books from a different file.");
            System.out.println("2. Display all books.");
            System.out.println("3. Search by title.");
            System.out.println("4. Filter by genre.");
            System.out.println("5. Exit");
            System.out.println("Enter an option number: ");

            try {
                choiceStr = input.nextLine();
                choice = Integer.parseInt(choiceStr);

                switch (choice) {
                    case 1:
                        clearScreen();
                        booksFilePath = promptValidFileName();
                        library.loadBooksFromFile(booksFilePath);
                        break;
                    case 2:
                        clearScreen();
                        library.displayAllBooks();
                        System.out.println("Press enter to go back to main menu...");
                        input.nextLine();
                        break;
                    case 3:
                        clearScreen();
                        System.out.println("Enter a book title: ");
                        String title = input.nextLine();
                        library.searchByTitle(title);
                        System.out.println("Press enter to go back to main menu...");
                        input.nextLine();
                        break;
                    case 4:
                        System.out.println("Available genres: ");
                        String genres = Arrays.stream(Book.Genre.values()).
                                map(g -> g.name().substring(0,1) + g.name().substring(1).toLowerCase()).
                                collect(Collectors.joining(", "));
                        System.out.print("[");
                        System.out.print(genres);
                        System.out.println("]");
                        Book.Genre genre = promptValidGenre();
                        library.filterByGenre(genre);
                        System.out.println("Press enter to go back to main menu...");
                        input.nextLine();
                        break;
                    case 5:
                        break outerLoop;
                    default:
                        System.out.println("Enter a valid option between 1 and 5.");

                }
            }

            catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number between 1 and 5.");
            }

            catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
                System.out.println("Reenter a valid choice.");
            }

        }while (true);

    }

    public static void clearScreen() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    public static String promptValidFileName() {

        File file;
        String path;

        do {
            System.out.println("Enter the file path: ");
            path = input.nextLine();
            file = new File(path);

            if (!file.exists())
                System.out.println("Error: file does not exist. Try again");
            else if (!file.isFile())
                System.out.println("Not a regular file. Try again.");
            else if (!file.canRead())
                System.out.println("Error: file can't be read. Check permissions and try again.");
            else
                break;
        } while (true);

        System.out.println("File " + file.getAbsolutePath() + " is a valid file. loading books....");
        System.out.println("Press Enter");
        input.nextLine();

        return path;
    }

    public static Book.Genre promptValidGenre() {

        while (true){
            String genreInput;
            System.out.println("Enter a genre: ");
            genreInput = input.nextLine().toUpperCase().trim();

            try {
                return Book.Genre.valueOf(genreInput);
            } catch (IllegalArgumentException ex) {
                System.out.println("Not a valid genre!");
            }


        }
    }

}

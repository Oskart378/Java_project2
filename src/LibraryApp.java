public class LibraryApp {

    public static void main(String[] args) throws Exception {
        Library lib = new Library();

        lib.loadBooksFromFile("src/books.txt");
        lib.searchByTitle("Verde");
    }
}

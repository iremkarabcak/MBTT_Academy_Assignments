//import java.util.ArrayList;
import java.util.*;

class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) {
        super(message);
    }
}
public class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
      //  System.out.println("Book added: " + book.getTitle());
    }

    public void borrowBook(String bookTitle) {
        try {
            Book book = findBook(bookTitle);
            if (!book.isBorrowed()) {
                book.borrowed();
            } else {
                System.out.println("Sorry, the book is already borrowed.");
            }
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void returnBook(String bookTitle) {
        try {
            Book book = findBook(bookTitle);
            if (book.isBorrowed()) {
                book.returned();
                System.out.println("you successfully  returned");
            } else {
                System.out.println("The book is not rented, so it cannot be returned.");
            }
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printAvailableBooks() {
        boolean anyAvailableBooks = false;
        for (Book book : books) {
            if (!book.isBorrowed()) {
                System.out.println(book.getTitle());
                anyAvailableBooks = true;
            }
        }
        if (!anyAvailableBooks) {
            System.out.println("No books available at the moment.");
        }
    }

    private Book findBook(String bookTitle) throws BookNotFoundException {
        for (Book book : books) {
            if (book.getTitle().equals(bookTitle)) {
                return book;
            }
        }
        throw new BookNotFoundException("Sorry, the book is not available in this library.");
    }

    public static void main(String[] args) {
        Library firstLibrary = new Library();
        Library secondLibrary = new Library();

        // Add four books to the first library
        firstLibrary.addBook(new Book("The Pragmatic Programmer"));
        firstLibrary.addBook(new Book("Code - The Hidden Language"));
        firstLibrary.addBook(new Book("Programming Pearls"));
        firstLibrary.addBook(new Book("Domain Driven Design in Life"));

        // Try to borrow The Pragmatic Programmer from both libraries
        System.out.println("Borrowing The Pragmatic Programmer:");
        firstLibrary.borrowBook("The Pragmatic Programmer");
        firstLibrary.borrowBook("The Pragmatic Programmer");
        secondLibrary.borrowBook("The Pragmatic Programmer");
        System.out.println();

        // Print the titles of all available books from both libraries
        System.out.println("Books available in the first library:");
        firstLibrary.printAvailableBooks();
        System.out.println();
        System.out.println("Books available in the second library:");
        secondLibrary.printAvailableBooks();
        System.out.println();

        // Return The Pragmatic Programmer to the first library
        System.out.println("Returning The Pragmatic Programmer:");
        firstLibrary.returnBook("The Pragmatic Programmer");
        System.out.println();

        // Print the titles of available books from the first library
        System.out.println("Books available in the first library:");
        firstLibrary.printAvailableBooks();
    }
}

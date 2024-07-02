import java.util.*;

class Book {
    private String id;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        isIssued = issued;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isIssued=" + isIssued +
                '}';
    }
}

class Member {
    private String id;
    private String name;
    private List<Book> borrowedBooks;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

public class LibraryManagementSystem {
    private static Map<String, Book> books = new HashMap<>();
    private static Map<String, Member> members = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add New Book");
            System.out.println("2. Register New Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. View Available Books");
            System.out.println("6. Search Book");
            System.out.println("7. Search Member");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    addNewBook(scanner);
                    break;
                case 2:
                    registerNewMember(scanner);
                    break;
                case 3:
                    issueBook(scanner);
                    break;
                case 4:
                    returnBook(scanner);
                    break;
                case 5:
                    viewAvailableBooks();
                    break;
                case 6:
                    searchBook(scanner);
                    break;
                case 7:
                    searchMember(scanner);
                    break;
                case 8:
                    System.out.println("Thank you for using the Library Management System!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addNewBook(Scanner scanner) {
        System.out.print("Enter book ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();

        books.put(id, new Book(id, title, author));
        System.out.println("Book added successfully!");
    }

    private static void registerNewMember(Scanner scanner) {
        System.out.print("Enter member ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter member name: ");
        String name = scanner.nextLine();

        members.put(id, new Member(id, name));
        System.out.println("Member registered successfully!");
    }

    private static void issueBook(Scanner scanner) {
        System.out.print("Enter member ID: ");
        String memberId = scanner.nextLine();
        Member member = members.get(memberId);
        if (member == null) {
            System.out.println("Invalid member ID.");
            return;
        }

        System.out.print("Enter book ID: ");
        String bookId = scanner.nextLine();
        Book book = books.get(bookId);
        if (book == null) {
            System.out.println("Invalid book ID.");
            return;
        }

        if (book.isIssued()) {
            System.out.println("Book is already issued.");
        } else {
            book.setIssued(true);
            member.borrowBook(book);
            System.out.println("Book issued successfully!");
        }
    }

    private static void returnBook(Scanner scanner) {
        System.out.print("Enter member ID: ");
        String memberId = scanner.nextLine();
        Member member = members.get(memberId);
        if (member == null) {
            System.out.println("Invalid member ID.");
            return;
        }

        System.out.print("Enter book ID: ");
        String bookId = scanner.nextLine();
        Book book = books.get(bookId);
        if (book == null) {
            System.out.println("Invalid book ID.");
            return;
        }

        if (!book.isIssued()) {
            System.out.println("Book is not issued.");
        } else {
            book.setIssued(false);
            member.returnBook(book);
            System.out.println("Book returned successfully!");
        }
    }

    private static void viewAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books.values()) {
            if (!book.isIssued()) {
                System.out.println(book);
            }
        }
    }

    private static void searchBook(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        System.out.println("Search Results:");
        for (Book book : books.values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println(book);
            }
        }
    }

    private static void searchMember(Scanner scanner) {
        System.out.print("Enter member name: ");
        String name = scanner.nextLine();

        System.out.println("Search Results:");
        for (Member member : members.values()) {
            if (member.getName().equalsIgnoreCase(name)) {
                System.out.println(member);
            }
        }
    }
}
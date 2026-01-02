public class Book {
    String title;
    String author;
    String isbn;
    int yearPublished;
    boolean isBorrowed = false;

    public Book(String title, String author, String isbn, int yearPublished) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.yearPublished = yearPublished;
    }
}

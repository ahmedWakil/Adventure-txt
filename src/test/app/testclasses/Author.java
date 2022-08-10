package testclasses;

import java.util.List;

public class Author {
    private String authorName;
    private List<Books> books;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder("\nAuthor name: " + authorName + "\nBooks:\n");
        for (Books book : books) {
            result.append("\n\t");
            result.append("Book Title: ").append(book.getTitle());
            result.append("\n\t");
            if (book.isInPrint()) {
                result.append("Status: is in print");
            } else {
                result.append("Status: is not in print");
            }
            result.append("\n");
        }

        return result.toString();
    }
}

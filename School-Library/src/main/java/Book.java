import java.util.List;

public class Book {

    private final String name;
    private final String author;
    private int quantity;

    public Book(String name, String author, int quantity) {
        this.name = name;
        this.author = author;
        this.quantity = quantity;

    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int quantityInLibrary() {
        return quantity;
    }

    public void updateQuantity(int quantity) {
        this.quantity += quantity;
    }

    @Override
    public String toString() {
        return
                String.format("\"%s\" by %s", getName(), getAuthor());
                //String.format(getName());
    }
}
import java.util.ArrayList;
import java.util.List;

public class Person implements Comparable<Person> {

    private String name;
    private String type;
    private List<String> bookBorrowed = new ArrayList<>();
    private int orderId;

    public Person(String name, String type) {
        this.name = name;
        this.type = type;
        if (this.type.equalsIgnoreCase("teacher")) this.orderId = 2225;
        if (this.type.equalsIgnoreCase("seniorStudent")) this.orderId = 2224;
        if (this.type.equalsIgnoreCase("juniorStudent")) this.orderId = 2223;

    }


    public String getName() {
        return name;
    }

    public List<String> getBookBorrowed() {
        return bookBorrowed;
    }

    public void setBookBorrowed(String bookBorrowed) {
        this.bookBorrowed.add(bookBorrowed);
    }

    public void removeBookBorrowed(String bookBorrowed){
        this.bookBorrowed.remove(bookBorrowed);
    }

    public String getType() {
        return type;
    }

    @Override
    public int compareTo(Person o) {
        return o.orderId > this.orderId? 1: -1;
    }

    @Override
    public String toString() {
        return
                String.format("  [name= %s, type= %s, bookBorrowed= %s] ", name, type, bookBorrowed);
    }
}

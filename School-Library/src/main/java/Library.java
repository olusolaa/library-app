import java.util.*;

public class Library {

    private final Map<String, Book> listOfBooks;
    private final HashMap<String, Person> listOfRegisteredUser;
    private final List <Person> fifoQueue = new ArrayList<>();
    private final Queue<Person> priorityQueue = new PriorityQueue<>();
    private final List <Person>borrowerList = new ArrayList<>();
    private Book inLibrary;

    public Library() {
        this.listOfBooks = new HashMap<>();
        this.listOfRegisteredUser = new HashMap<>();
    }

    public void addBook(Book book){
        if (book != null) {
            inLibrary = listOfBooks.getOrDefault(book.getName(), book);
            if (inLibrary != book){
                inLibrary.updateQuantity(book.quantityInLibrary());
                System.out.printf("\n\n%d more copies of %s added, total copies available is %s\n",
                        book.quantityInLibrary(), inLibrary.getName(), inLibrary.quantityInLibrary());
            } else listOfBooks.put(book.getName(), book);
        }
    }

    public void registerUser(Person person){
        listOfRegisteredUser.put(person.getName(), person);
        System.out.printf("Registration completed. %s borrow limit is 2 books.\n", person.getName());
    }
    public void addToQueue(Person user, String queueType) {
        if (listOfRegisteredUser.containsKey(user.getName()) && Collections.frequency(getBorrowerList(),user) <2)
            if (queueType == "priority")
                priorityQueue.add(user);
            else if (queueType == "fifo")
                fifoQueue.add(user);
        else System.out.println(user.getName() +", isn't a registered user or has exceeded borrow limit.");
    }


    public List<Person> lendBookWithPriority(String nameOfBook) {
        while (!priorityQueue.isEmpty()) {
            lend(priorityQueue.poll(), nameOfBook);
        }return borrowerList;
    }

    public List lendBookWithFIFO(String nameOfBook) {
        for (int i = 0; i< fifoQueue.size(); i++) {
            lend(fifoQueue.get(i), nameOfBook);
        }return borrowerList;
    }

    private void lend(Person borrower, String nameOfBook){
        inLibrary = listOfBooks.getOrDefault(nameOfBook, null);
        if (inLibrary != null && !borrower.getBookBorrowed().contains(inLibrary) && inLibrary.quantityInLibrary() != 0) {
            System.out.printf("%d copies of %s now available \n", get(nameOfBook).quantityInLibrary(), get(nameOfBook));
            borrower.setBookBorrowed(nameOfBook);
            System.out.printf("%s, borrowed a copy of \"%s\" and can borrow %d more books\n",borrower.getName(), nameOfBook, (2-borrower.getBookBorrowed().size()));
            inLibrary.updateQuantity(-1);
            borrowerList.add(borrower);
        } else System.out.printf("Check back %s, \"%s\" is not available.\n", borrower.getName(),nameOfBook);
    }

    private Book get(String key){
        return listOfBooks.get(key);
    }

    public void returnBook(String bookTitle, Person borrower){
            inLibrary = listOfBooks.getOrDefault(bookTitle, null);
            if (inLibrary != (null) && borrower.getBookBorrowed().contains(bookTitle)) {
                inLibrary.updateQuantity(1);
                borrower.removeBookBorrowed(bookTitle);
                borrowerList.remove(borrower);
                System.out.printf("Book returned %s can now borrow %d books",borrower.getName(), 2-borrower.getBookBorrowed().size());
            }
    }

    public HashMap<String, Person> getListOfRegisteredUser() {
        return listOfRegisteredUser;
    }

    public Map<String, Book> getListOfBooks() {
        return listOfBooks;
    }

    public List<Person> getFifoQueue() {
        return fifoQueue;
    }

    public Queue<Person> getPriorityQueue() {
        return priorityQueue;
    }

    public List<Person> getBorrowerList() {
        return borrowerList;
    }
}

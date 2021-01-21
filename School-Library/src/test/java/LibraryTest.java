import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LibraryTest {


    Library library = new Library();
    Person seniorStudent = new Person("Alao", "seniorStudent");
    Person juniorStudent = new Person("Olusola","juniorStudent");
    Person juniorStudent2 = new Person("Temi","juniorStudent");
    Person teacher = new Person("Daro", "teacher");
    Person nonmember = new Person("Ayo", "nonStudent");

    Book supply1 = new Book("Things falls apart", "Chinau", 4);
    Book supply2 = new Book("Joy of MotherHood", "Agbaje", 2);
    Book supply3 = new Book("Things falls apart", "Chinau", 5);

    @BeforeEach
    void setUp(){
        library.registerUser(teacher);
        library.registerUser(juniorStudent);
        library.registerUser(seniorStudent);
        library.registerUser(juniorStudent2);

        library.addToQueue(juniorStudent, "priority");
        library.addToQueue(teacher, "priority");
        library.addToQueue(juniorStudent2,"priority");
        library.addToQueue(seniorStudent, "priority");
        library.addToQueue(nonmember, "priority");

        library.addToQueue(juniorStudent, "fifo");
        library.addToQueue(teacher, "fifo");
        library.addToQueue(seniorStudent, "fifo");


        library.addBook(supply1);
        library.addBook(supply2);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("can add new books and update quantities of existing books")
    void addBook() {
        assertEquals(2, library.getListOfBooks().size());
        assertEquals(4, library.getListOfBooks().get("Things falls apart").quantityInLibrary());

        library.addBook(supply3); //5 more quantity was added
        assertEquals(9, library.getListOfBooks().get("Things falls apart").quantityInLibrary());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("can register users")
    void registerUser() {
        assertEquals(4,library.getListOfRegisteredUser().size());
        assertEquals("teacher", library.getListOfRegisteredUser().get("Daro").getType());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("can add users to queues based on priority or FIFO")
    void addToQueue() {
        assertEquals(4, library.getPriorityQueue().size()); //unregistered person can't be added
        assertEquals(3,library.getFifoQueue().size());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("can lend book with priority queue")
    void lendBookWithPriority() {
        library.lendBookWithPriority("Things falls apart");
        assertEquals("teacher", library.getBorrowerList().get(0).getType());
        assertEquals("seniorStudent", library.getBorrowerList().get(1).getType());
        assertEquals("juniorStudent", library.getBorrowerList().get(2).getType());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("can lend book with FIFO queue")
    void lendBookWithFIFO() {
        library.lendBookWithFIFO("Things falls apart");
        assertEquals("juniorStudent", library.getBorrowerList().get(0).getType());
        assertEquals("teacher", library.getBorrowerList().get(1).getType());
        assertEquals("seniorStudent", library.getBorrowerList().get(2).getType());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("can return book")
    void returnBook() {
        library.lendBookWithPriority("Things falls apart");
        library.returnBook("Things falls apart", juniorStudent);
        assertTrue(!library.getBorrowerList().contains(juniorStudent));
    }

}
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Person juniorStudent = new Person("Olusola","juniorStudent");
        Person seniorStudent = new Person("Alao", "seniorStudent");
        Person teacher = new Person("Daro", "teacher");
        Person nonmember = new Person("Ayo", "nonStudent");

        Library library = new Library();

        library.registerUser(seniorStudent);
        library.registerUser(juniorStudent);
        library.registerUser(teacher);


        library.addToQueue(juniorStudent, "priority");
        library.addToQueue(teacher, "priority");
        library.addToQueue(seniorStudent, "priority");
        library.addToQueue(nonmember, "priority");

 //       library.lendBookWithPriority("Things falls apart");

        Book book1 = new Book("Things falls apart", "Chinau", 7);
        library.addBook(book1);

        System.out.println("\n");
        library.lendBookWithPriority("Things falls apart");

        library.addToQueue(juniorStudent, "fifo");
        library.addToQueue(teacher, "fifo");
        library.addToQueue(seniorStudent, "fifo");


        Book book2 = new Book("Joy of MotherHood", "Agbaje", 2);
        library.addBook(book2);

        System.out.println("\n");
        library.lendBookWithFIFO("Joy of MotherHood");


        System.out.println("\n");
        library.returnBook("Joy of MotherHood", juniorStudent);

        Book book3 = new Book("Things falls apart", "Chinau", 5);
        library.addBook(book3);

        System.out.println("\n******** List of Library Users ********");
        library.getListOfRegisteredUser().forEach((key, value) -> System.out.println(key + ":" + value));

        System.out.println("\n******** List of Books in Library ********");
        library.getListOfBooks().forEach((key, value) -> System.out.println(key + ":" + value));
    }
}

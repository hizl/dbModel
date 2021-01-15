import dao.BooksDAO;
import entity.BookModel;
import model.BooksDaoImpliments;

import java.util.List;
import java.util.Scanner;

public class Application {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        BooksDAO booksDao = new BooksDaoImpliments();

        List<BookModel> findBooks = booksDao.findAll();
        findBooks.forEach(System.out::println);

        System.out.println("****************************\n");

        System.out.println(booksDao.findBy(scanner.nextInt()));
        //you can hardcode the value

        System.out.println("****************************\n");


    }
}

import dao.BooksDAO;
import entity.BookModel;
import model.ModelImplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("Menu");

            BooksDAO booksDAO = new ModelImplementation();


            String command = scanner.nextLine();


            switch (command) {
                case ("save"): {
                    BookModel bookModel = new BookModel();


                    bookModel.setTitle("New Book " );
                    bookModel.setPagesCount(999);
                    System.out.println("SAVE" + booksDAO.save(bookModel));

                    break;
                }


                case ("find"): {

                    List<BookModel> findBooks = booksDAO.findAll();
                    findBooks.forEach(System.out::println);

                    System.out.println("****************************\n");
                    break;
                }
                case ("findby"): {

                    System.out.println(booksDAO.findBy(scanner.nextInt()));


                    System.out.println("****************************\n");
                    break;
                }
                case ("delete"): {
                    booksDAO.delete(scanner.nextInt());
                    System.out.println("delete\n");
                    break;
                }

                case ("exit"): {
                    running = false;
                    System.out.println("exit");
                }
            }
        }
    }
}
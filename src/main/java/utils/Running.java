package utils;

import dao.BooksDAO;
import entity.BookModel;
import model.ModelImplementation;


import java.util.List;
import java.util.Scanner;

public class Running extends Thread {

    static Scanner scanner = new Scanner(System.in);
    static String COMMAND;
    static BooksDAO booksDAO = new ModelImplementation();


    public void run() {

        System.out.println("MAIN Menu");

        while (true) {
            COMMAND = scanner.nextLine();

            switch (COMMAND) {

                case ("save"): {
                    BookModel bookModel = new BookModel();

                    System.out.println("write name new book ");
                    bookModel.setTitle(scanner.nextLine());

                    System.out.println("write page count");
                    bookModel.setPagesCount(scanner.nextInt());
                    System.out.println("SAVING YOUR BOOK " + booksDAO.save(bookModel));

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
                    System.out.println("delete book\n");
                    break;
                }
            }
        }
    }
}
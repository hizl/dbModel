package model;

import dao.BooksDAO;
import entity.BookModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ModelImplementation implements BooksDAO {


    //request from database
    private static final String FIND_ALL_STATEMENT = "SELECT ID, TITLE, PAGES_COUNT FROM BOOKS_TABLE;";
    private static final String FIND_BY_ID_STATEMENT = "SELECT ID, TITLE, PAGES_COUNT FROM BOOKS_TABLE WHERE ID=?";
    private static final String DELETE_BY_ID_STATEMENT = "DELETE FROM BOOKS_TABLE WHERE ID=?";
    private static final String SAVE_STATEMENT = "INSERT INTO BOOKS_TABLE(TITLE, PAGES_COUNT) VALUES(?,?);";


    // obtain CONNECTION session with using " work context entry" for connection database and using it
    Connection getConnection() {
        try {
            System.out.println("connecting to a postgresql database\n");
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/books_db", //change your books_db ""
                    "postgreadmin", //change your user ""
                    "123"); //and password ""
        } catch (SQLException e) {
            System.err.println("Can't be connection ====\n" + e.getMessage());
        }
        return null;
    }


    @Override
    public List<BookModel> findAll() {

        Connection connection = getConnection();

        List<BookModel> bookList = new ArrayList<>();
        try {

            PreparedStatement pstmt = connection.prepareStatement(FIND_ALL_STATEMENT);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {

                BookModel booksModel = new BookModel();

                Integer id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                Integer pagesCount = resultSet.getInt(3);

                booksModel.setId(id);
                booksModel.setTitle(title);
                booksModel.setPagesCount(pagesCount);

                bookList.add(booksModel);

            }


        } catch (SQLException e) {
            System.err.println("Exception while getting books\n" + e.getMessage());
            close(connection);
        }
        close(connection);
        return bookList;
    }


    @Override
    public BookModel findBy(Integer id) {
        BookModel bookModel = null;
        Connection connection = getConnection();

        try {
            PreparedStatement prepsStatement = connection.prepareStatement(FIND_BY_ID_STATEMENT);

            prepsStatement.setInt(1, id);

            ResultSet resultSet = prepsStatement.executeQuery();

            while (resultSet.next()) {

                bookModel = new BookModel();

                String title = resultSet.getString("TITLE");
                Integer pagesCount = resultSet.getInt("PAGES_COUNT");

                bookModel.setId(id);
                bookModel.setTitle(title);
                bookModel.setPagesCount(pagesCount);
            }


        } catch (SQLException e) {
            System.err.println("ERROR " + e.getMessage());
            close(connection);
        }
        close(connection);
        return bookModel;
    }

    @Override
    public BookModel save(BookModel bookModel) {
        Connection connection = getConnection();
        try {


            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_STATEMENT);

           // preparedStatement.setInt(1, bookModel.getId());
            preparedStatement.setString(1, bookModel.getTitle());
            preparedStatement.setInt(2, bookModel.getPagesCount());


            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            System.out.println("Error saving " + e.getMessage() + "\n");
            close(connection);
        }


        close(connection);
        return bookModel;
    }

    @Override
    public void delete(Integer id) {

        Connection connection = getConnection();

        try {
            PreparedStatement prepsStatement = connection.prepareStatement(DELETE_BY_ID_STATEMENT);

            prepsStatement.setInt(1, id);

            prepsStatement.executeUpdate();

            close(connection);

        } catch (SQLException e) {
            System.err.println("Exception while saving the book " + e.getMessage());
            close(connection);
        }
    }


    public static void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
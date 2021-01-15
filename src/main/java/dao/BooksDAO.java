package dao;

import entity.BookModel;

import java.util.List;

public interface BooksDAO {

    List<BookModel> findAll();

    BookModel findBy(Integer id);

    BookModel save(BookModel bookModel);

    void delete(Integer id);


}

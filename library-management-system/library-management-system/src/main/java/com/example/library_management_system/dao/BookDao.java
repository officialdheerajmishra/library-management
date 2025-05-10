package com.example.library_management_system.dao;

import com.example.library_management_system.entity.Book;
import com.example.library_management_system.rowMapper.BookRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDao {

    private JdbcTemplate jdbcTemplate;

    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // To save the book
    public Book save(Book book) {

        String query = "INSERT INTO books(title, about, author, language, available) VALUES(?, ?, ?, ?, ?);";

        int rowAffected = jdbcTemplate.update(
                query,
                book.getTitle(),
                book.getAbout(),
                book.getAuthor(),
                book.getLanguage(),
                book.getAvailable()
        );

        System.out.println("book added: " + rowAffected);
        return book;


    }

    // To delete the book
    public void delete(int id) {

        String query = "DELETE FROM books WHERE id = ?";

        int rowAffected = jdbcTemplate.update(
                query,
                id
        );

        System.out.println("book deleted: " + rowAffected);

    }

    // To update the book
    public void update(int id, Book newBook) {

        String query = "UPDATE books SET title = ?, about = ?, author = ?, language = ?, available = ? WHERE id= ?";

        int rowAffected = jdbcTemplate.update(
                query,
                newBook.getTitle(),
                newBook.getAbout(),
                newBook.getAuthor(),
                newBook.getLanguage(),
                newBook.getAvailable(),
                id
        );

        System.out.println("book updated: " + rowAffected);

    }

    // To get single book
    public Book getBook(int id) {

        String query = "SELECT * FROM books WHERE id = ?";

        Book book = jdbcTemplate.queryForObject(
                query,
                new BookRowMapper(),
                id
        );

        return book;

    }

    // To get All book
    public List<Book> getAllBook() {

        String query = "SELECT * FROM books;";

        List<Book> books = jdbcTemplate.query(
                query,
                new BookRowMapper()
        );

        return books;
    }

    // To search book
    public List<Book> searchBook(String titleKeyword) {

        String query = "SELECT * FORM books WHERE title like ?";

        List<Book> books = jdbcTemplate.query(
                query,
                new BookRowMapper(),
                "%" + titleKeyword + "%"
        );

        return books;
    }

}

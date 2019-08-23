package database;

import java.sql.SQLException;
import java.util.ArrayList;

import models.Book;

public interface BookDAO {
	public int addBook(Book book) throws SQLException;
	public Book getBookById(int id) throws SQLException;
	public ArrayList<Book> getAllBooks() throws SQLException;
	public int deleteBookById(int id) throws SQLException;
	public int updateBookById(int id, String column, String data) throws SQLException;
}

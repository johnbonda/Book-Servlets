package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Book;

public class BookDAOImplementation implements BookDAO {
	private Connection connection;
	
	public BookDAOImplementation() throws IOException, SQLException {
		this.connection = DBConnector.getConnector().getConnection();
	}

	@Override
	public int addBook(Book book) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO Book VALUES (?,?,?,?)";
		PreparedStatement sqlStatement = this.connection.prepareStatement(sql);
		sqlStatement.setInt(1, book.getId());
		sqlStatement.setString(2, book.getTitle());
		sqlStatement.setString(3, book.getAuthor());
		sqlStatement.setDouble(4, book.getPrice());
		return sqlStatement.executeUpdate();
	}

	@Override
	public Book getBookById(int id) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM Book where id = ?";
		PreparedStatement sqlStatement;
		ResultSet resultSet;
		Book book = null;
		sqlStatement = this.connection.prepareStatement(sql);
		sqlStatement.setInt(1, id);
		resultSet = sqlStatement.executeQuery();
		if(resultSet.next()) {
			book = new Book(
					resultSet.getInt("id"),
					resultSet.getString("title"),
					resultSet.getString("author"),
					resultSet.getDouble("price"));
		}
		return book;
	}

	@Override
	public ArrayList<Book> getAllBooks() throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM Book";
		PreparedStatement sqlStatement;
		ResultSet resultSet;
		ArrayList<Book> books = new ArrayList<Book>();
		sqlStatement = this.connection.prepareStatement(sql);
		resultSet = sqlStatement.executeQuery();
		while(resultSet.next()) {
			books.add(new Book(
					resultSet.getInt("id"),
					resultSet.getString("title"),
					resultSet.getString("author"),
					resultSet.getDouble("price")));
		}
		return books;
	}

	@Override
	public int deleteBookById(int id) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "delete FROM Book where id = ?";
		PreparedStatement sqlStatement;
		sqlStatement = this.connection.prepareStatement(sql);
		sqlStatement.setInt(1, id);
		return sqlStatement.executeUpdate();
	}

	@Override
	public int updateBookById(int id, String column, String data) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update Book set " + column + " = ? where id = ?";
		PreparedStatement sqlStatement;
		sqlStatement = this.connection.prepareStatement(sql);
		if(column == "price")
			sqlStatement.setDouble(1, Double.parseDouble(data));
		else
			sqlStatement.setString(1, data);
		sqlStatement.setInt(2, id);
		return sqlStatement.executeUpdate();
	}

}

package servelets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.BookDAO;
import database.BookDAOImplementation;
import models.Book;

/**
 * Servlet implementation class AddBook
 */
@WebServlet("/AddBook")
public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		int result = 0;
		Book book = new Book(
				Integer.parseInt(request.getParameter("id")),
				request.getParameter("title"),
				request.getParameter("author"),
				Double.parseDouble(request.getParameter("price")));
		try {
			BookDAO bookOperations = new BookDAOImplementation();
			result = bookOperations.addBook(book);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			switch(e.getErrorCode()) {
			case 1062:
				out.println("Book Id already exists");
				break;
			default: 
				out.println(e.getLocalizedMessage());
			}
			return;
		}
		if(result == 1) {
			String resultString = "";
		
			resultString +="Book id: " + book.getId() + "<br>";
			resultString +="Book title: " + book.getTitle() + "<br>";
			resultString +="Book author: " + book.getAuthor() + "<br>";
			resultString +="Book price: " + book.getPrice() + "<br>";
			resultString += "<br>";
		
			out.println(resultString);
			out.println("Book Added Successfully");
			return;
		}
		out.println("Could not add book");
	}
}

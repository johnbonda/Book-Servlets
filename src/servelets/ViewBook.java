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
 * Servlet implementation class ViewBook
 */
@WebServlet("/ViewBook")
public class ViewBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		Book book = null;
		try {
			BookDAO bookOperations = new BookDAOImplementation();
			book = bookOperations.getBookById(Integer.parseInt(request.getParameter("id")));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(book == null) {
			out.println("Book not found");
			return;
		}
		String result = "";
		
			result +="Book id: " + book.getId() + "<br>";
			result +="Book title: " + book.getTitle() + "<br>";
			result +="Book author: " + book.getAuthor() + "<br>";
			result +="Book price: " + book.getPrice() + "<br>";
			result += "<br>";
		
		out.println(result);
	}

}

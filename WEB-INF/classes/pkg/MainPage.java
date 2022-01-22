package pkg;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/MainPage"})
public class MainPage extends HttpServlet{

	
	public void doGet (HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException {
        response.setContentType("text/html");    // Set the content type
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/MainPage.jsp");
        dispatcher.forward(request, response);

    }

    public void doPost (HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException {
        response.setContentType("text/html");    // Set the content type
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/MainPage.jsp");
        dispatcher.forward(request, response);
	
 
    }

}


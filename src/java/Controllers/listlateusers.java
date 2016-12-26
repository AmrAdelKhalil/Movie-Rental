package Controllers;

import Models.AdminModel;
import Models.MovieModel;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Date;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "listlateusers", urlPatterns = {"/listlateusers"})
public class listlateusers extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        int movieId = Integer.parseInt(request.getParameter("movieId"));
        
        AdminModel admin = new AdminModel();
        HashMap<String, HashMap<String,String> > not_ack_users = admin.getLateUsers(movieId);
        MovieModel movie_model = new MovieModel();

        request.setAttribute("users", not_ack_users);
        request.setAttribute("movie", movie_model.showMovie(movieId, -1));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/listlateusers.jsp");
        dispatcher.include(request, response);
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}

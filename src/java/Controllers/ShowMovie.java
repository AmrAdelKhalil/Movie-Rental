package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Models.MovieModel;
import Models.StaffModel;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ShowMovie", urlPatterns = {"/ShowMovie"})
public class ShowMovie extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session =request.getSession(true);
       
        int id = Integer.parseInt(request.getParameter("id"));
        int userId = -1;
        if (session.getAttribute("userId") != null){
            userId = (int) session.getAttribute("userId");
        }
        
        MovieModel movieModel = new MovieModel();
        HashMap<String,String> movie = movieModel.showMovie(id, userId); 
        HashMap<String,String> movie_staff = new StaffModel().getStaff(id);
        
        request.setAttribute("movie", movie);
        request.setAttribute("staff", movie_staff);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/ShowMovie.jsp");
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

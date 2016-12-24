package Controllers;

import Models.MovieModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdateMovie", urlPatterns = {"/UpdateMovie"})
public class UpdateMovie extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        HashMap<String,String>values=new HashMap<String,String>();
        values.put("name",request.getParameter("movieName"));
        values.put("category",request.getParameter("category"));
        values.put("description",request.getParameter("description"));
        values.put("duration",request.getParameter("duration"));
        values.put("price",request.getParameter("price"));
        values.put("year",request.getParameter("year"));
        values.put("quality",request.getParameter("quality"));
        new MovieModel().updateMovie(values);
        response.sendRedirect("updateMovie.jsp");

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

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

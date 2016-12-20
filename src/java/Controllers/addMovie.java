package Controllers;

import Models.MovieModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "addMovie", urlPatterns = {"/addMovie"})
public class addMovie extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        try  {
         HashMap<String,String>values=new HashMap();
            values.put("movieName", request.getParameter("movieName"));
            values.put("category",request.getParameter("category"));
            values.put("description",request.getParameter("description"));
            values.put("duration", request.getParameter("duration"));
            values.put("price", request.getParameter("price"));
            MovieModel movie=new MovieModel();
            movie.addMovie(values);
            
            HashMap<String,String>movieStaff=new HashMap();
            int number=Integer.parseInt(request.getParameter("Number"));
            for(int i=0;i<number;i++)
            {
                movieStaff.put("name"+i, request.getParameter("memberName"+i));
                movieStaff.put("role"+i, request.getParameter("role"+i));
            }
            
           movie.addMovieStaff(movieStaff,number);
            response.sendRedirect("addMovie.jsp");
        
        } catch (SQLException ex) {
            Logger.getLogger(addMovie.class.getName()).log(Level.SEVERE, null, ex);
        }
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

package Controllers;

import Models.MovieModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdateStaff", urlPatterns = {"/UpdateStaff"})
public class UpdateStaff extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             HashMap<String,String>movieStaff=new HashMap<String,String>();
            int number=Integer.parseInt(request.getParameter("Number"));
            for(Integer i=0;i<number;i++)
            {   
                movieStaff.put("id"+i.toString(), request.getParameter("id"+i.toString()));
                movieStaff.put("name"+i.toString(), request.getParameter("memberName"+i.toString()));
                movieStaff.put("role"+i.toString(), request.getParameter("role"+i.toString()));
            }
            MovieModel movie=new MovieModel();
           movie.updateStaff(movieStaff,number);
             response.setContentType("text/html");
        request.setAttribute("id", request.getParameter("id"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("ShowMovie");
        dispatcher.forward(request, response);
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

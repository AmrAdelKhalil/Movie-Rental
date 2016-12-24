package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Models.MovieModel;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ShowMovie", urlPatterns = {"/ShowMovie"})
public class ShowMovie extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session =request.getSession(true);
        
        int id = Integer.parseInt(request.getParameter("id"));
        int userId = (int) session.getAttribute("userId");
        HashMap<String,String> movie = new MovieModel().showMovie(id, userId);        
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
    }// </editor-fold>

}

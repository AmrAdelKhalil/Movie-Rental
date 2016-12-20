package Controllers;

import Models.MovieModel;
import Models.UserModel;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "RentMovie", urlPatterns = {"/RentMovie"})
public class RentMovie extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int userId = Integer.parseInt(request.getParameter("userId"));
        int movieId = Integer.parseInt(request.getParameter("movieId"));
        float rentPeriod = Float.parseFloat(request.getParameter("rentPeriod"));
        float totalPrice = Float.parseFloat(request.getParameter("totalPrice"));
        
        UserModel user = new UserModel();
        user.rentMovie(userId, movieId, rentPeriod, totalPrice);
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

package Controllers;

import Models.UserModel;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "ExtendRentingMovie", urlPatterns = {"/ExtendRentingMovie"})
public class ExtendRentingMovie extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session =request.getSession(true);
        
        int userId = (int) session.getAttribute("userId");
        int movieId = (int) session.getAttribute("movieId");
        float extendedPeriod = Float.parseFloat(request.getParameter("rentPeriod"));
        float extendedPrice = Float.parseFloat(request.getParameter("totalPrice"));
        
        UserModel user = new UserModel();
        user.extendRentingMovie(userId, movieId, extendedPeriod, extendedPrice);
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

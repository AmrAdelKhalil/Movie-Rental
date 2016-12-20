package Controllers;

import Models.MovieModel;
import Models.UserModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "RentMovie", urlPatterns = {"/RentMovie"})
public class RentMovie extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        
        HttpSession session =request.getSession(true);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        
        int userId = (int) session.getAttribute("userId");
        int movieId = (int) session.getAttribute("movieId");
        Date startDate = (Date) formatter.parse(request.getParameter("startDate"));
        Date endDate = (Date) formatter.parse(request.getParameter("endDate"));
        float totalPrice = Float.parseFloat(request.getParameter("totalPrice"));
        
        UserModel user = new UserModel();
        user.rentMovie(userId, movieId, startDate, endDate, totalPrice);
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(RentMovie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(RentMovie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

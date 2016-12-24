package Controllers;

import Models.UserModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "beforeUpdateSettings", urlPatterns = {"/beforeUpdateSettings"})
public class beforeUpdateSettings extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HashMap<String, String> user = new UserModel().showSettings(Integer.parseInt(request.getParameter("id")));
        
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/UpdateSettings.jsp");
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

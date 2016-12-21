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

@WebServlet(name = "UpdateSettings", urlPatterns = {"/UpdateSettings"})
public class UpdateSettings extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String creditCard = request.getParameter("creditCard");
        String newPassword = request.getParameter("newPassword");
        
        HashMap<String, String> user = null;
        if(new UserModel().updateSettings(id, name, email, password, creditCard, newPassword)){
            user = (HashMap<String, String>)new UserModel().showSettings(id);
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/ShowSettings.jsp");
            dispatcher.include(request, response);
        }else{
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/UpdateSettings.jsp");
            dispatcher.include(request, response);
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
    }// </editor-fold>

}

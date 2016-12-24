package Controllers;

import Models.AdminModel;
import Models.UserModel;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "SignUp", urlPatterns = {"/SignUp"})
public class SignUp extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String isAdmin = request.getParameter("isAdmin");
        
        HttpSession session = request.getSession(true);
        session.setAttribute("name", name);
        session.setAttribute("email", email);
        
        if(isAdmin.equals("on")){
            AdminModel admin = new AdminModel();
            admin.signUp(name, email, password);
            admin.login(email, password);
            session.setAttribute("isAdmin", true);
        }
        else{    
            UserModel user = new UserModel();
            String credit = request.getParameter("credit");
            user.signUp(name, email, password, credit);
            user.login(email, password);
            session.setAttribute("isAdmin", false);
            
        }
        
        
        
        
        
        
        response.sendRedirect("Views/newjsp.jsp");
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

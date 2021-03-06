package Controllers;

import Models.AdminModel;
import Models.UserModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
        
        if(isAdmin != null && isAdmin.equals("on")){
            AdminModel admin = new AdminModel();
            HashMap<String,String> map = admin.signUp(name, email, password);
            session.setAttribute("isAdmin", true);
            session.setAttribute("userId", Integer.parseInt(map.get("userId")));
            
        }
        else{    
            UserModel user = new UserModel();
            String credit = request.getParameter("credit");
            HashMap<String,String> map = user.signUp(name, email, password, credit);
            session.setAttribute("userId", Integer.parseInt(map.get("userId")));
            
        }
        
        response.sendRedirect("Views/index.jsp");
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.AdminModel;
import Models.UserModel;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String isAdmin = request.getParameter("isAdmin");
        
        HttpSession session = request.getSession(true);
        session.setAttribute("email", email);
        
        if(isAdmin.equals("on")){
            AdminModel admin = new AdminModel();
            HashMap<String,String> map = admin.login(email, password);
            session.setAttribute("userId", Integer.parseInt(map.get("userId")));
            session.setAttribute("name", map.get("name"));
            session.setAttribute("isAdmin", true);
            
        }else{
            UserModel user = new UserModel();
            HashMap<String, String> map = user.login(email, password);
            session.setAttribute("userId", Integer.parseInt(map.get("userId")));
            session.setAttribute("name", map.get("name"));
        
            
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

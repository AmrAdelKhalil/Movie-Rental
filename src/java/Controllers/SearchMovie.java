package Controllers;

import Models.MovieModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SearchMovie", urlPatterns = {"/SearchMovie"})
public class SearchMovie extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HashMap<String,String>values=new HashMap();
        values.put("name", request.getParameter("keyword"));
        values.put("quality", request.getParameter("quality"));
        values.put("category", request.getParameter("genre"));
        values.put("rating",request.getParameter("rating"));
        values.put("order_by", request.getParameter("order_by"));
        MovieModel movie= new MovieModel();
        movie.seqQuerySearch(values);
        response.sendRedirect("/Views/resultSearch.jsp");
            
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

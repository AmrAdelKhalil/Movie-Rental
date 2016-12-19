package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class addMovie_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en-US\" xmlns=\"http://www.w3.org/1999/xhtml\" dir=\"ltr\">\n");
      out.write("<head>\n");
      out.write("\t<title>Free CSS template by ChocoTemplates.com</title>\n");
      out.write("\t<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />\n");
      out.write("\t<link rel=\"stylesheet\" href=\"css/style.css\"  media=\"all\" />\n");
      out.write("\t<script src=\"angular.min.js\"></script>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<!-- Shell -->\n");
      out.write("<div id=\"shell\">\n");
      out.write("\t<!-- Header -->\n");
      out.write("\t<div id=\"header\">\n");
      out.write("\t\t<h1 id=\"logo\"><a href=\"#\">Movie Hunter</a></h1>\n");
      out.write("\t\t<div class=\"social\">\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t\t<br>\n");
      out.write("\t\t\t\t<a class=\"user-data\" href=\"#\"> Amr Alaa</a>\n");
      out.write("\t\t\t\t<a class=\"user-data\" href=\"#\"> Settings</a>\n");
      out.write("\t\t\t\t<a class=\"user-data\" href=\"#\"> Logout</a>\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t\n");
      out.write("\t\t</div>\n");
      out.write("\t\t\n");
      out.write("\t\t<!-- Navigation -->\n");
      out.write("\t\t<div id=\"navigation\">\n");
      out.write("\t\t\t<ul>\n");
      out.write("\t\t\t    <li><a class=\"active\" href=\"#\">HOME</a></li>\n");
      out.write("\t\t\t    <li><a href=\"#\">NEWS</a></li>\n");
      out.write("\t\t\t    <li><a href=\"#\">IN THEATERS</a></li>\n");
      out.write("\t\t\t    <li><a href=\"#\">COMING SOON</a></li>\n");
      out.write("\t\t\t    <li><a href=\"#\">CONTACT</a></li>\n");
      out.write("\t\t\t    <li><a href=\"#\">ADVERTISE</a></li>\n");
      out.write("\t\t\t</ul>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<!-- end Navigation -->\n");
      out.write("\t\t\n");
      out.write("\t\t<!-- Sub-menu -->\n");
      out.write("\t\t<div id=\"sub-navigation\">\n");
      out.write("\t\t\t<ul>\n");
      out.write("\t\t\t    <li><a href=\"#\">SHOW ALL</a></li>\n");
      out.write("\t\t\t    <li><a href=\"#\">LATEST TRAILERS</a></li>\n");
      out.write("\t\t\t    <li><a href=\"#\">TOP RATED</a></li>\n");
      out.write("\t\t\t    <li><a href=\"#\">MOST COMMENTED</a></li>\n");
      out.write("\t\t\t</ul>\n");
      out.write("\t\t\t<div id=\"search\">\n");
      out.write("\t\t\t\t<form action=\"home_submit\" method=\"get\" accept-charset=\"utf-8\">\n");
      out.write("\t\t\t\t\t<label for=\"search-field\">SEARCH</label>\t\t\t\t\t\n");
      out.write("\t\t\t\t\t<input type=\"text\" name=\"search field\" value=\"Enter search here\" id=\"search-field\" title=\"Enter search here\" class=\"blink search-field\"  />\n");
      out.write("\t\t\t\t\t<input type=\"submit\" value=\"GO!\" class=\"search-button\" />\n");
      out.write("\t\t\t\t</form>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<!-- end Sub-Menu -->\n");
      out.write("\t\t\n");
      out.write("\t</div>\n");
      out.write("\t<!-- end Header -->\n");
      out.write("\t<div ng-app=\"myApp\" ng-controller=\"controller\">\n");
      out.write("            <form method=\"post\" action=\"addMovie\">\n");
      out.write("                <input type=\"text\" name=\"movieName\" placeholder=\"Movie Name\" >\n");
      out.write("                <input type=\"text\" name=\"category\" placeholder=\"Category\">\n");
      out.write("                <input type=\"text\" name=\"description\" placeholder=\"Description for Movie\">\n");
      out.write("                <input type=\"text\" name=\"duration\" placeholder=\"Duration in min\">\n");
      out.write("                <input type=\"text\" name=\"price\" placeholder=\"Price Per Day\">\n");
      out.write("                <label>Number Of Staff</label>\n");
      out.write("                <input type=\"number\" name=\"Number\" ng-model=\"Number\" min=0 value=\"0\">\n");
      out.write("                <ul>\n");
      out.write("                    <li ng-repeat=\"i in total(Number) track by $index\">\n");
      out.write("                        <input type=\"text\" name=\"memberName{{$index}}\" placeholder=\"Member Name\" >\n");
      out.write("                        <input type=\"text\" name=\"role{{$index}}\" placeholder=\"Role\">\n");
      out.write("                        <label>------------------------------------------------------------------------------------</label>\n");
      out.write("                    </li>\n");
      out.write("                </ul>\n");
      out.write("                <input type=\"submit\" name=\"submit\" value=\"Add Movie\">\n");
      out.write("            </form>\n");
      out.write("\t</div>\n");
      out.write("</div>\n");
      out.write("<script src=\"angular.js\"></script>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

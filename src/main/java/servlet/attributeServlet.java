// From "Professional Java Server Programming", Patzer et al.,

// Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.http.*;

// Import Java Libraries
import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.util.Enumeration;

@WebServlet(name = "attributeServlet", urlPatterns = {"/attributeServlet"})
public class attributeServlet extends HttpServlet
{
public void doGet (HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException
{
   // Get session object
   HttpSession session = request.getSession();

   String name   = request.getParameter("attrib_name");
   String value  = request.getParameter("attrib_value");
   String remove = request.getParameter("attrib_remove");
   String action = request.getParameter("action");

    if (remove != null && remove.equals("on")) {
      session.removeAttribute(name);
    } else if(action != null && action.equals("invalidate")) {
        session.invalidate();
    } else {
      if ((name != null && name.length() > 0) && (value != null && value.length() > 0))
      {
         session.setAttribute(name, value);
      }
    }



   response.setContentType("text/html");
   PrintWriter out = response.getWriter();

   out.println("<html>");
   // no-cache lets the page reload by clicking on the reload link
   out.println("<meta http-equiv=\"Pragma\" content=\"no-cache\">");
   out.println("<head>");
   out.println(" <title>Session lifecycle</title>");
   out.println("</head>");
   out.println("");

   out.println("<body>");
   out.println("<h1><center>Session attributes</center></h1>");

   out.println("Enter name and value of an attribute");

   // String url = response.encodeURL ("offutt/servlet/attributeServlet");
   String url = response.encodeURL("attributeServlet");
   out.println("<form action=\"" + url + "\" method=\"GET\">");
   out.println(" Name: ");
   out.println(" <input type=\"text\" size=\"10\" name=\"attrib_name\">");

   out.println(" Value: ");
   out.println(" <input type=\"text\" size=\"10\" name=\"attrib_value\">");

   out.println(" <br><input type=\"checkbox\" name=\"attrib_remove\">Remove");
   out.println(" <input type=\"submit\" name=\"update\" value=\"Update\">");
   out.println("</form>");
      String lifeCycleURL = "";
      out.print  ("<a href=\"" + lifeCycleURL + "?action=invalidate\">");
      out.println("Invalidate the session</a>");
   out.println("<hr>");

   out.println("Attributes in this session:");
   Enumeration e = session.getAttributeNames();
   while (e.hasMoreElements())
   {
      String att_name  = (String) e.nextElement();
      String att_value = (String) session.getAttribute(att_name);

      out.print  ("<br><b>Name:</b> ");
      out.println(att_name);
      out.print  ("<br><b>Value:</b> ");
      out.println(att_value);
   } //end while
    

   out.println("</body>");
   out.println("</html>");
   out.close();
} // End doGet
} //End  SessionLifeCycle

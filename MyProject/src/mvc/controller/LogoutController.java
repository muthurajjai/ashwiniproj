package mvc.controller;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class LogoutController extends HttpServlet
{
                private static final long serialVersionUID = 1L;
                protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
                { 
                                HttpSession session = request.getSession(false); 
                                if(session!=null) 
                                {
                                                session.invalidate(); //removes all session attributes bound to the session
                                                request.setAttribute("errMessage", "You have logged out successfully");
                                                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Login.jsp");
                                                requestDispatcher.forward(request, response);
                                                System.out.println("Logged out");
                                }
                }
}


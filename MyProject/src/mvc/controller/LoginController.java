
package mvc.controller;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import mvc.DAO.LoginDAO;
import mvc.bean.LoginBean;
import mvc.bean.RegisterBean;

public class LoginController extends HttpServlet {

                public LoginController() {
                }

                protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

                                

                                String userName = request.getParameter("username");
                                String password = request.getParameter("password");

                                LoginBean loginBean = new LoginBean(); 

                                loginBean.setUserName(userName); 
                                loginBean.setPassword(password);

                                LoginDAO loginDao = new LoginDAO(); 
                                String userValidate = loginDao.authenticateUser(loginBean); 
                                HttpSession session=request.getSession();
                                if(userValidate.equals("SUCCESS")) 
                                {
                                                RegisterBean regBean=(RegisterBean)session.getAttribute("RegBean"); 
                                                session.setAttribute("RegBean",regBean);
                                                request.setAttribute("userName", userName); 
                                                request.getRequestDispatcher("/Home.jsp").forward(request, response);
                                }
                                else
                                {
                                                request.setAttribute("errMessage", userValidate); 
                                                request.getRequestDispatcher("/Login.jsp").forward(request, response);
                                }
                }

}

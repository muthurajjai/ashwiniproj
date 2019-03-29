package mvc.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import mvc.DAO.RegistratorDAO;
import mvc.bean.RegisterBean;

public class RegisterController extends HttpServlet {
                private static final long serialVersionUID = 1L;

                protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                                response.setContentType("text/html");  
                                PrintWriter out = response.getWriter();  
                                          
                                String user=request.getParameter("uname");
                                String fname=request.getParameter("fname");
                                String lname=request.getParameter("lname");
                                String pass=request.getParameter("pass");
                                String pass1=request.getParameter("pass1");
                                String email=request.getParameter("email");  
                                long contact = 0;
                                String address=request.getParameter("address");
                                String state=request.getParameter("state");
                                String fullAddress=address+","+state;
                                String[] hobbies = request.getParameterValues("hob");
                                //List<String> list = Arrays.asList(hobbies);
                                String hob =" ";
                                for(int i=0;i<hobbies.length;i++){
                                                                hob+=hobbies[i]+" ";
                                                                                
                                }
//                            for(String hb: list){
//            hob1=hb+",";
//            System.out.println(hob1);
//        }
//        
                                ArrayList al = new ArrayList();
                                if ((user == null) || (user.equals(""))) {
            al.add("Enter User Name...");
        } 
        
        if ((fname == null) || (fname.equals(""))) {
            al.add("Enter First Name...");
        }

        if ((lname == null) || (lname.equals(""))) {
            al.add("Enter Last Name...");
        }
        if ((pass == null) || (pass.equals(""))) {
            al.add("Enter Password...");
        }
        if ((pass1 == null) || (pass1.equals(""))) {
            al.add("Re-Type Password...");
        }
        if ((pass == pass1) || (pass1.equals("pass"))) {
            al.add("Password doesnot match...");
        }
        if ((email == null) || (email.equals(""))) {
            al.add("Enter Email...");
        } 
        boolean b=email.matches("[a-zA-Z0-9\\.]+@[a-zA-Z0-9\\-\\_\\.]+\\.[a-zA-Z0-9]{3}");
                                if(b==false) {
                                                al.add("Enter proper Email ID...");
                                                //System.out.println("Email is Valid");
                                }
       
        if ((address == null) || (address.equals(""))) {
            al.add("Enter Address...");
        } 
        if ((state == null) || (state.equals(""))) {
            al.add("select state...");
        }
        if(request.getParameter("contact").length()!=10){
                al.add("Contact number must be 10 digits...");
        }
        
        if (request.getParameter("contact").length() == 0 ){
                 al.add("Enter Contact Number...");
       }else {
           try {
                   contact = Long.parseLong(request.getParameter("contact"));
           } catch (NumberFormatException nfe) {
               al.add("Enter contact number - Integer Value...");
           }
       }
        if (al.size() != 0) {
            out.println("<h2><b style='font: bold;color: red;'>"+al+"</h2></b>");
        } else {
                try {
                RegisterBean regBean= new RegisterBean();
                regBean.setUser(user);
                regBean.setAddress(fullAddress);
                regBean.setContact(contact);       
                regBean.setEmail(email);
                regBean.setFname(fname);
                regBean.setLname(lname);
                regBean.setPassword(pass);
                regBean.setHobbies(hob);
                RegistratorDAO dao= new RegistratorDAO();
                String msg=dao.insertUserDetails(regBean);
                HttpSession session=request.getSession();
                
                if (msg.equals("SUCCESS")) {
                                session.setAttribute("RegBean",regBean);
                out.println("Registration Successful.");
                request.getRequestDispatcher("/Login.jsp").forward(request, response);
            } else {
                out.println("Registration not successful.");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            }
                }
                catch (Exception e) {
                response.sendError(503, "PROBLEM IN DATABASE...");
            }
        }
                }
}

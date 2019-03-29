package mvc.DAO;

import java.sql.*;
import mvc.DAO.DBConnection;
import mvc.bean.LoginBean;

public class LoginDAO {
                public String authenticateUser(LoginBean loginBean)
                {
                                String userName = loginBean.getUserName(); //Keeping user entered values in temporary variables.
                                String password = loginBean.getPassword();
                                Connection con = null;
                                Statement statement = null;
                                ResultSet resultSet = null;
                                String userNameDB = "";
                                String passwordDB = "";
                                try
                                {
                                                con = DBConnection.createConnection(); 
                                                statement = con.createStatement(); 
                                                resultSet = statement.executeQuery("select user,password from register_user"); 
                                                while(resultSet.next()) 
                                                {
                                                                userNameDB = resultSet.getString("user"); 
                                                                passwordDB = resultSet.getString("password");
                                                                if(userName.equals(userNameDB) && password.equals(passwordDB))
                                                                {
                                                                                return "SUCCESS"; 
                                                                }
                                                }
                                }
                                                catch(SQLException e)
                                                {
                                                                e.printStackTrace();
                                                }
                                                return "Invalid user credentials"; 
                                
                }
                
}

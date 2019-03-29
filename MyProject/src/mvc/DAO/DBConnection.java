package mvc.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
                        public static Connection createConnection()
                        {
                                                Connection con = null;
                                                try 
                                                {
                                                                        try 
                                                                        {
                                                                                                Class.forName("com.mysql.jdbc.Driver"); 
                                                                        } 
                                                                        catch (ClassNotFoundException e)
                                                                        {
                                                                                                e.printStackTrace();
                                                                        } 
                                                                        con= DriverManager.
                    getConnection("jdbc:mysql://localhost/emp?","root","root");
                                                                        
                                                } 
                                                catch (Exception e) 
                                                {
                                                                        e.printStackTrace();
                                                }
                                                return con; 
                        }
}

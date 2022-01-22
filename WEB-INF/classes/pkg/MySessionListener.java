package pkg;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import pkg.ConfigBean;  // ConfigBean.java created in pakage
import javax.sql.*;
import java.sql.*;
import java.util.*;
import java.io.*;
import javax.servlet.annotation.WebListener;

@WebListener
public class MySessionListener implements HttpSessionListener{
    private  int totalActiveSessions=0;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        totalActiveSessions++;
        System.out.println("Session Created ! Current session: "+totalActiveSessions);
    }
    
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        totalActiveSessions--;
        HttpSession session= se.getSession();
      
        long now = new java.util.Date().getTime();  // set current time as now.
        // check wherether the timeout is true
        boolean timeout = (now - session.getLastAccessedTime()) >= ((long)session.getMaxInactiveInterval() * 1000L);

        if(timeout){
            String query = "INSERT INTO GAME VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); " ;  //insert 26 columns into sql        
            try(Connection con = ConfigBean.getConnection();    //step1
                
                PreparedStatement ps = con.prepareStatement(query);){ 
                ps.setString(1, session.getAttribute("username").toString());
                ps.setString(2, session.getAttribute("passphrase").toString());
                               
                String [] face= new String [12];
                double [] value = new double [12];
                for (int i = 0; i<12;i++){
                    face [i] = session.getAttribute("timeOutStore"+i).toString();
                    value[i] = (double)session.getAttribute("timeOutStoreValue"+i);
                }    

                int j = 0;  int z = 0;
                for(int i = 3;i<= 25; i = i+2  ){                
                    ps.setString(i, face [j]);
                    j++;               
                }    

                for(int i = 4;i<= 26; i = i+2  ){                
                    ps.setDouble(i,value [z]); 
                    z++;               
                } 
                ps.executeUpdate();  
                ps.close();  
                con.close();    
            }
                
            catch(SQLException e){
                System.err.println(e.getMessage());
                System.err.println(e.getStackTrace());

            }
        }
        System.out.println("End session"+totalActiveSessions);
        

    }
}

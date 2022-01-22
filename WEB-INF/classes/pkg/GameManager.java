package pkg;
import pkg.ConfigBean;  // ConfigBean.java created in pakage

import javax.sql.*;
import java.sql.*;
import java.util.*;
import java.io.*;

public class GameManager implements Serializable{

    private Case[] c = new Case[12];    //create new case arry and load data in
    private String [] caseFace = new String [12];
    private Double [] caseValue = new Double [12];
    private boolean exist = false;  // default username and passphrase doesnt exist
    private boolean userexist = false;      // only check for username
    private String username;
    private String passphrase;

    public boolean checkusername(String username ){ 
        String query = "SELECT * FROM GAME WHERE username = '"+username+"'";
        // get a connection from the class created as 'ConfigBean.java'
        try(Connection con = ConfigBean.getConnection();    //step1
        //Create a statement 
        Statement sta = con.createStatement();          //step2
        //get result from execute the statement with the SELECT query
        ResultSet rs = sta.executeQuery(query);)  {      //step3

        if (rs.next()){     // make boolean exist = ture if rs.next() is true
            userexist = true;                                       
        }
        rs.close();
        sta.close();
        con.close();            // close all connection
    }
        catch(SQLException e){            
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }            
        return userexist;    

    }    
//=============================================================================    
    public boolean checkuser(String username,String passphrase ){ 
        String query = "SELECT * FROM GAME WHERE username = '"+username+"' and passphrase = '"+passphrase+"'";
        // get a connection from the class created as 'ConfigBean.java'
        try(Connection con = ConfigBean.getConnection();    //step1
        //Create a statement 
        Statement sta = con.createStatement();          //step2
        //get result from execute the statement with the SELECT query
        ResultSet rs = sta.executeQuery(query);)  {      //step3

        if (rs.next()){     // make boolean exist = ture if rs.next() is true
            exist = true;
                                       
        }
        rs.close();
        sta.close();
        con.close();
        
    }
        catch(SQLException e){
            
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        } 
                   
        return exist;    

    }    
//=============================================================================
    public void insert(String username,String passphrase,GameClass StoreGame){

        String query = "INSERT INTO GAME VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); " ;  //insert 26 columns into sql 

        
        try(Connection con = ConfigBean.getConnection();    //step1
            
            PreparedStatement ps = con.prepareStatement(query);){ 
            ps.setString(1, username);
            ps.setString(2, passphrase);
            
            int j = 0;  int z = 0;
            for(int i = 3;i<= 25; i = i+2  ){                
                ps.setString(i, StoreGame.showCase(j));
                j++;               
            }    

            for(int i = 4;i<= 26; i = i+2  ){                
                ps.setDouble(i, StoreGame.getValue(z)); 
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




//================================================================================  
    // this is to read data .
    public void read(String username,String passphrase ){    //String name,String passphase

    String query = "SELECT * FROM GAME WHERE username = '"+username+"' and passphrase = '"+passphrase+"'";
    // get a connection from the class created as 'ConfigBean.java'
    try(Connection con = ConfigBean.getConnection();    //step1
        //Create a statement 
        Statement sta = con.createStatement();          //step2
        //get result from execute the statement with the SELECT query
        ResultSet rs = sta.executeQuery(query);)  {      //step3

        while (rs.next()){
            
            username = rs.getString("username");
            passphrase = rs.getString("passphrase");

            for(int i=0;i<c.length;i++){
                caseFace[i] = rs.getString("case"+i+"Face"); //store sql case face into caseFace[]
                caseValue[i] = rs.getDouble("case"+i+"Value");
                c[i] = new Case(caseValue[i], caseFace[i]);
            }                    
                              
        }
        
        rs.close();
        sta.close();
        con.close();

    }
        catch(SQLException e){
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }

  }
//================================================================================  
// delete user data for new Game
public void deleteData(String username,String passphrase ){ 
    String query = "DELETE FROM GAME WHERE username = '"+username+"' and passphrase = '"+passphrase+"'";
    // get a connection from the class created as 'ConfigBean.java'
    try(Connection con = ConfigBean.getConnection();    //step1
        Statement sta = con.createStatement();          //step2
        ResultSet rs = sta.executeQuery(query);)  {      //step3        
        exist = false;  //set exist to false, cause data been deleted
        
        rs.close();
        sta.close();
        con.close();

    }
        catch(SQLException e){
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }
        
}




//================================================================================  
    public Case[] getStoreCase() {
        return c;   // retrurn the stored cases.
    }

    public void setUserName(String username){
        this.username = username;
    }
    public String getUserName(){
        return username;
    }

    public void setPassphrase(String passphrase){
        this.passphrase = passphrase;
    }
    public String getPassphrase(){
        return passphrase;
    }

    public void restexist(){
        exist = false;
        
    }
    
    public void restuserexist(){
        userexist = false;
        
    }






    





}




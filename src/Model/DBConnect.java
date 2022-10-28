package Model;

import java.sql.*;

public class DBConnect {
    
    private Connection conn;
    private Statement st;
    private String url = "jdbc:mysql://localhost:3306/mvc2";
    private String user = "root";
    private String password = "";
    
    public ResultSet getResult(String query)//ใช้ในการดึงข้อมูลจาก DATABASE
    {
        ResultSet rs;
        try{
            conn = DriverManager.getConnection(url, user, password);
            st = conn.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception ex){
            rs = null;
        }
        return rs ;
    }
    
    public boolean execute(String query)//ใช้ในการส่งคำสั่งไปยัง DATABASE
    {
        boolean rs;
        try{
            conn = DriverManager.getConnection(url, user, password);
            st = conn.createStatement();
            st.execute(query);
            rs=true;
        }catch(Exception ex){
            rs=false;
        }
        return rs;
    }
    
    public void close()
    {
        try{
            conn.close();
        }catch(Exception ex){
            
        }
    }
}

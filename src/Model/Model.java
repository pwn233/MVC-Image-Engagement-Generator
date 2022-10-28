/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.sql.ResultSet;
import java.util.Random;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author User
 */
public class Model {
    private int like,share,comment;
    private int reach = 0;
    private int[] data = new int[3];
    private String viral;
    public void generator() {
        Random rd = new Random();
        reach = rd.nextInt(100000-1000)+1000;
        like = rd.nextInt(100000)+1;
        share = rd.nextInt(100000)+1;
        comment = rd.nextInt(100000)+1;
        while(like > reach) {
            like = rd.nextInt(100000)+1;
        }
        while(share > reach) {
            share = rd.nextInt(100000)+1;
        }
        while(comment > reach) {
            comment = rd.nextInt(100000)+1;
        }
        data[0] = like;
        data[1] = share;
        data[2] = comment;
    }
    public int[] getAll() {
        return data;
    }
    public void viralCal() {
        if(Double.valueOf((data[0] + data[1] + data[2])/reach) > 0.1) {
            viral = "yes";
        } else {
            viral = "no";
        }
    }
    public String insertDataToDB(String p) {
        String query ="";
        String status ="";
        query = String.format("INSERT INTO meme(ID, PATH, REACH, LIKES, SHARE, COMMENT, VIRAL)VALUES(null , '%1$s', '%2$d', '%3$d', '%4$d', '%5$d', '%6$s')", p, reach, data[0], data[1], data[2], viral );
        try {
            DBConnect conn = new DBConnect();
            boolean result = conn.execute(query);
            if (result) {
                status = "complete";
            } else {
                status = "fail";
            }
            conn.close();
        } catch (Exception ex) {
            status = "Error Update: " + ex;
        }
        return status;
    }
    public DefaultTableModel showData(DefaultTableModel model) { 
        String query;
        try {
            DBConnect conn = new DBConnect();
            query = "SELECT * FROM meme"; 
            ResultSet rs = conn.getResult(query);
            while (rs.next()) {
                String id = Integer.toString(rs.getInt("ID"));
                String ph = rs.getString("PATH");
                String rh = Integer.toString(rs.getInt("REACH"));
                String le = Integer.toString(rs.getInt("LIKES"));
                String se = Integer.toString(rs.getInt("SHARE"));
                String ct = Integer.toString(rs.getInt("COMMENT"));
                String vr = rs.getString("VIRAL");
                String[] row = {id, ph, rh, le, se, ct, vr};
                model.addRow(row);
                // critical variable name, beware of it!
            }
            conn.close();
        } catch (Exception ex) {
            //sss
        }
        return model;
    }
}

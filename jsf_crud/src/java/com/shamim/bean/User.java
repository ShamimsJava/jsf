package com.shamim.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class User {
    private int id;
    private String fname, lname, username, password;
    private Map<String, Object> map = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    public User() {
    }

    public User(int id, String fname, String lname, String username, String password) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Connection getConnection(){
        Connection con = null;      
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lr", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }        
        return con;
    }
    
    public String insert(){
        String sql = "insert into user(fname, lname, username, password) values(?, ?, ?, ?)";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, getFname());
            ps.setString(2, getLname());
            ps.setString(3, getUsername());
            ps.setString(4, getPassword());
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
            return "insert.xhtml?faces-redirect=true";
    }
    
     public String update(User u){
        int status = 0;
        String sql = "update user set fname = ?, lname = ?, username = ?, password = ? where id = ?";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, u.getFname());
            ps.setString(2, u.getLname());
            ps.setString(3, u.getUsername());
            ps.setString(4, u.getPassword());
            ps.setInt(5, u.getId());
            status = ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }       
        return "display.xhtml?faces-redirect=true";
    }
    
      public String delete(int id){
        String sql = "delete from user where id = ?";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }       
        return "display.xhtml?faces-redirect=true";
    }
     
    public List<User> getAllRecords(){
        List<User> list = new ArrayList<>();
        String sql = "select * from user";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User u = new User(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"), rs.getString("username"), rs.getString("password"));
                list.add(u);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }       
        return list;
    }
    
    public String getRecordById(int id){
        User u = null;       
        String sql = "select * from user where id = ?";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                u = new User(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"), rs.getString("username"), rs.getString("password"));
                map.put("editUser", u);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }       
        return "update.xhtml?faces-redirect=true";
    }
}

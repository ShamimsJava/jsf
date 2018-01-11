package com.shamim.bean;

import static com.shamim.dao.UserDao.getConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class User {
    private int id;
    private String fname, lname, username, password;

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
    
     public String save(){
        int status = 0;
        String sql = "insert into user(fname, lname, username, password) values(?, ?, ?, ?)";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, getFname());
            ps.setString(2, getLname());
            ps.setString(3, getUsername());
            ps.setString(4, getPassword());
            status = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }       
        return "index.xhtml?faces-redirect=true";
    }
}

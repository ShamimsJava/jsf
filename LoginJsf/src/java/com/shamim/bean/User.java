package com.shamim.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class User {
    int id;
    String fname, lname, username, password;

    public User() {
    }
    
    public User(int id, String fname, String lname, String username, String password) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
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

    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lr", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public String validate() {
        boolean status = false;
        String sql = "select * from user where username = ? and password = ?";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            status = rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(status){
            return "welcome.xhtml?faces-redirect=true";
        }else{
            return "error.xhtml?faces-redirect=true";
        }
    }
    
    public String save() {
        int status = 0;
        String sql = "insert into user(fname, lname, username, password) values(?, ?, ?, ?)";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, username);
            ps.setString(4, password);
            status = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(status>0){
            return "registration.xhtml?faces-redirect=true";  
        }else{
            return "error.xhtml?faces-redirect=true";
        }
    }
}

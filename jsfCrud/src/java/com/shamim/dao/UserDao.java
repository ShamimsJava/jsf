package com.shamim.dao;

import com.shamim.bean.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class UserDao {
    public static Connection getConnection(){
        Connection con = null;      
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lr", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }        
        return con;
    }
    
    public static boolean validate(User u){
        boolean status = false;
        String sql = "select * from user where username = ? and password = ?";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ResultSet rs = ps.executeQuery();
            status = rs.next();
        } catch (Exception e) {
        }       
        return status;
    }
    
    public static int save(){
        User u = new User();
        int status = 0;
        String sql = "insert into user(fname, lname, username, password) values(?, ?, ?, ?)";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, u.getFname());
            ps.setString(2, u.getLname());
            ps.setString(3, u.getUsername());
            ps.setString(4, u.getPassword());
            status = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }       
        return status;
    }
    
    public static int update(User u){
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
        } catch (Exception e) {
            e.printStackTrace();
        }       
        return status;
    }
    
    public static int delete(User u){
        int status = 0;
        String sql = "delete from user where id = ?";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, u.getId());
            status = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }       
        return status;
    }
    
    public static List<User> getAllRecords(){
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
        } catch (Exception e) {
            e.printStackTrace();
        }       
        return list;
    }
    
    public static User getRecordById(int id){
        User u = null;
        String sql = "select * from user where id = ?";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                u = new User(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"), rs.getString("username"), rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }       
        return u;
    }
}

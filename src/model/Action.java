/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.ConnectionHandle;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MyPC
 */
public class Action {
    ConnectionHandle model = new ConnectionHandle();
    public boolean addStudent(Student s){
        
        String sql = "INSERT INTO Students(ID, name, address, phone, email, mark) "
                + "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = model.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, s.getID());
            ps.setString(2, s.getName());
            ps.setString(3, s.getAddress());
            ps.setString(4, s.getPhone());
            ps.setString(5, s.getEmail());
            ps.setFloat(6, s.getMark());
            
            return ps.executeUpdate() > 0;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public boolean deleteStudent(String s){
        String sql = "DELETE FROM students where ID = '" + s + "'";
        
        try {
            PreparedStatement ps = model.getInstance().getConnection().prepareStatement(sql);
            return ps.executeUpdate()> 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean modifiedStudent(Student st, String s){
        String sql = "UPDATE students SET ID = ?, name = ?, address = ?, phone = ?, email = ?, mark = ? WHERE ID ='" + s + "'";
        try {            
            PreparedStatement ps = model.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, st.getID());
            ps.setString(2, st.getName());
            ps.setString(3, st.getAddress());
            ps.setString(4, st.getPhone());
            ps.setString(5, st.getEmail());
            ps.setFloat(6, st.getMark());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<Student> getSearchList(String s, String search){        
        
        ArrayList<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE " + s + " LIKE '%" + search + "%'";   
        try {
            PreparedStatement ps = model.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Student st = new Student();
                st.setID(rs.getString("ID"));
                st.setName(rs.getString("name"));
                st.setAddress(rs.getString("address"));
                st.setPhone(rs.getString("phone"));
                st.setEmail(rs.getString("email"));
                st.setMark(rs.getFloat("mark"));                
                list.add(st);                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    public ArrayList<Student> getListStudent(){
        ArrayList<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";
        
        try {
            PreparedStatement ps = model.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Student s = new Student();
                s.setID(rs.getString("ID"));
                s.setName(rs.getString("name"));
                s.setAddress(rs.getString("address"));
                s.setPhone(rs.getString("phone"));
                s.setEmail(rs.getString("email"));
                s.setMark(rs.getFloat("mark"));
                
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
}

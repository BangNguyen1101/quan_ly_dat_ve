/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Enum.Role;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class DAO_User {
    private static User user;
    
    public static List<User> getAllUsers() throws SQLException, ClassNotFoundException {
        List<User> users = new ArrayList<>();
        //--- Kết nối CSDL, lấy dữ liệu
        Connection conn = Config.DB.getConnection();
        //--- Xây dựng câu lệnh truy vấn 
        String sql = "Select * From user";
        //--- Thức thi câu lệnh truy vấn
        PreparedStatement pStmt = conn.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery();
        //--- Xử lí kết quả trả về
        while (rs.next()){
            User user = new User();
            user.setEmail(rs.getString("email"));
            user.setFullname(rs.getString("fullname"));
            user.setRole(rs.getString("role").compareTo(Role.Admin.toString()) == 0 ? Role.Admin : Role.User);
            users.add(user);
        }
        Config.DB.closePreparedStatement(pStmt);
        Config.DB.closeConnection(conn);
        return users;
    }
    
    public static User getUser(String email) throws SQLException, ClassNotFoundException {
        User user = new User();
        //--- Kết nối CSDL, lấy dữ liệu
        Connection conn = Config.DB.getConnection();
        //--- Xây dựng câu lệnh truy vấn 
        String sql = "Select * From user where email = '" + email +"'";
        //--- Thức thi câu lệnh truy vấn
        PreparedStatement pStmt = conn.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery();
        //--- Xử lí kết quả trả về
        while (rs.next()) {
            user.setId(Integer.valueOf(rs.getString("id")));
            user.setEmail(rs.getString("email"));
            user.setFullname(rs.getString("fullname"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role").compareTo(Role.Admin.toString()) == 0 ? Role.Admin : Role.User);
        }
        Config.DB.closePreparedStatement(pStmt);
        Config.DB.closeConnection(conn);
        return user;
    }
    
    public static int createUser(User user) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException  {
        //--- Kết nối CSDL, lấy dữ liệu
        Connection conn = Config.DB.getConnection();
        //--- Xây dựng câu lệnh truy vấn 
        String sql = "insert into User(email, password, fullname, role) values (N'"+user.getEmail()+"', N'"+user.getPassword()+"', '"+user.getFullname()+"', '"+user.getRole()+"')";
        //--- Thức thi câu lệnh truy vấn
        PreparedStatement pStmt = conn.prepareStatement(sql);
        int result = pStmt.executeUpdate();
        Config.DB.closePreparedStatement(pStmt);
        Config.DB.closeConnection(conn);
        return result;
    }
}

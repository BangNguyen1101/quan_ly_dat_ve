/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Movie;
import Enum.Role;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lemin
 */
public class DAO_Movie {
    public static List<Movie> getAll() throws SQLException, ClassNotFoundException {
        List<Movie> movies = new ArrayList<>();
        //--- Kết nối CSDL, lấy dữ liệu
        Connection conn = Config.DB.getConnection();
        //--- Xây dựng câu lệnh truy vấn 
        String sql = "Select * From movie";
        //--- Thức thi câu lệnh truy vấn
        PreparedStatement pStmt = conn.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery();
        //--- Xử lí kết quả trả về
        while (rs.next()){
           Movie _movie = new Movie(rs.getString("name"),rs.getString("description"),rs.getString("image"), Integer.parseInt(rs.getString("hour")),Integer.parseInt(rs.getString("minute")), rs.getString("video"));
           _movie.setId(Integer.valueOf(rs.getString("id")));
           movies.add(_movie);
        }
        Config.DB.closePreparedStatement(pStmt);
        Config.DB.closeConnection(conn);
        return movies;
    }
    
    public static int createMovie(Movie movie) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException  {
        //--- Kết nối CSDL, lấy dữ liệu
        Connection conn = Config.DB.getConnection();
        //--- Xây dựng câu lệnh truy vấn 
        String sql = "insert into Movie(name, description, image, hour, minute, video) values (N'"+movie.getName()+"', N'"+movie.getDescription()+"', '"+movie.getImage()+"', '"+movie.getHour()+"', '"+movie.getMinute()+"', '"+movie.getVideoTrailer()+"')";
        //--- Thức thi câu lệnh truy vấn
        PreparedStatement pStmt = conn.prepareStatement(sql);
        int result = pStmt.executeUpdate();
        Config.DB.closePreparedStatement(pStmt);
        Config.DB.closeConnection(conn);
        return result;
    }
    
    public static void updateMovie(Movie _movie) throws SQLException, ClassNotFoundException {
        //--- Kết nối CSDL, lấy dữ liệu
        Connection conn = Config.DB.getConnection();
        //--- Xây dựng câu lệnh truy vấn 
        String sql = "update Movie set name= N'"+_movie.getName()+"', description= N'"+_movie.getDescription()+"', hour='"+_movie.getHour()+"', minute=N'"+_movie.getMinute()+"', image='"+_movie.getImage()+"', video='"+_movie.getVideoTrailer()+"' where id = '"+_movie.getId()+"'";
        // -- Thực thi câu lệnh truy vấn
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.executeUpdate();
        Config.DB.closePreparedStatement(pStmt);
        Config.DB.closeConnection(conn);
    }
    
    public static void deleteMovie(int id) throws SQLException, ClassNotFoundException {
        //--- Kết nối CSDL, lấy dữ liệu
        Connection conn = Config.DB.getConnection();
        //--- Xây dựng câu lệnh truy vấn 
        String sql = "delete from movie where id = '"+id+"'";
        // -- Thực thi câu lệnh truy vấn
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.executeUpdate();
        Config.DB.closePreparedStatement(pStmt);
        Config.DB.closeConnection(conn);;
    }
}

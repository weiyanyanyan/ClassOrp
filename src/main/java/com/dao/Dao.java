package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by MaiBenBen on 2019/4/21.
 */
public class Dao {
    // 获取数据库连接
    public static Connection getConnection(){

        Connection conn = null;
        String driver = "com.mysql.jdbc.Driver";
        String username = "root";
        String password = "root";
        String dataBaseName = "class_orp";
        String url = "jdbc:mysql://localhost:3308/"+dataBaseName+"?user="
                + username + "&password=" + password + "&useUnicode=true&characterEncoding=UTF-8";
        try
        {
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
            System.out.println("数据库驱动加载出错");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("数据库出错");
        }
        return conn;
    }
    //关闭相关通道
    public static void close(ResultSet rs,PreparedStatement p,Connection conn)
    {
        try
        {
            if(!rs.isClosed()){
                rs.close();
            }
            if(!p.isClosed()){
                p.close();
            }
            if(!conn.isClosed()){
                conn.close();
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("数据关闭出错");
        }
    }
    //关闭相关通道
    public static void closeUpdate(PreparedStatement p,Connection conn)
    {
        try
        {
            if(!p.isClosed()){
                p.close();
            }
            if(!conn.isClosed()){
                conn.close();
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("数据关闭出错");
        }
    }
}

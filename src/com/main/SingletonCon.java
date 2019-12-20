package com.main;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class Demo {
    static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;
    private static final String URL = "jdbc:derby:fdb;create=true;";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";

    private static Connection con=null;
    public static Connection getConnection() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        if(con==null)
        {
            con=DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return con;
    }


//    private static void createConnection()
//    {
//        try
//        {
//            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
//            //Get a connection
//            conn = DriverManager.getConnection(URL, USER, PASSWORD);
//        }
//        catch (Exception except)
//        {
//            except.printStackTrace();
//        }
//    }
//
//    private static void selectRestaurants()
//    {
//        try
//        {
//            stmt = conn.createStatement();
//            ResultSet results = stmt.executeQuery("SELECT * FROM ADMIN.BOOKS");
//            ResultSetMetaData rsmd = results.getMetaData();
//            int numberCols = rsmd.getColumnCount();
//            for (int i=1; i<=numberCols; i++)
//            {
//                //print Column Names
//                System.out.print(rsmd.getColumnLabel(i)+"\t\t");
//            }
//
//            System.out.println("\n-------------------------------------------------");
//
//            while(results.next())
//            {
//                int id = results.getInt(1);
//                String restName = results.getString(2);
//                String cityName = results.getString(3);
//                System.out.println(id + "\t\t" + restName + "\t\t" + cityName);
//            }
//            results.close();
//            stmt.close();
//        }
//        catch (SQLException sqlExcept)
//        {
//            sqlExcept.printStackTrace();
//        }
//    }

    private static void shutdown()
    {
        try
        {
            if (stmt != null)
            {
                stmt.close();
            }
            if (conn != null)
            {
                DriverManager.getConnection(URL + ";shutdown=true");
                conn.close();
            }
        }
        catch (SQLException sqlExcept)
        {

        }

    }
}

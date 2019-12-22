package com.main;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class SingletonCon {
    static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    // jdbc Singleton Connection
    private static Connection conn = null;
    private static Statement stmt = null;
    private static final String URL = "jdbc:derby:fdb;create=true;";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";

    private static Connection con=null;
    // Getting Connection, jdbc driver
    public static Connection getConnection() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        if(con==null)
        {
            con=DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return con;
    }
}

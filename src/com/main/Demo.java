package com.main;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

//public class Demo
//{
//    private static final String URL = "jdbc:derby:addressbook";
//    private static final String USERNAME = "kk";
//    private static final String PASSWORD = "123456";
//
//    private Connection connection; // manages connection
//    private PreparedStatement selectAllPeople;
//    private PreparedStatement selectPeopleByLastName;
//    private PreparedStatement insertNewPerson;
//
//    // constructor
//    public BookQueries()
//    {
//        try
//        {
//            connection =
//                    DriverManager.getConnection(URL, USERNAME, PASSWORD);
//
//            // create query that selects all entries in the AddressBook
//            selectAllPeople =
//                    connection.prepareStatement("SELECT * FROM Addresses");
//
//            // create query that selects entries with a specific last name
//            selectPeopleByLastName = connection.prepareStatement(
//                    "SELECT * FROM Addresses WHERE LastName = ?");
//
//            // create insert that adds a new entry into the database
//            insertNewPerson = connection.prepareStatement(
//                    "INSERT INTO Addresses " +
//                            "(FirstName, LastName, Email, PhoneNumber) " +
//                            "VALUES (?, ?, ?, ?)");
//        }
//        catch (SQLException sqlException)
//        {
//            sqlException.printStackTrace();
//            System.exit(1);
//        }
//    } // end PersonQueries constructor
//
//    // select all of the addresses in the database
//    public List< Person > getAllPeople()
//    {
//        List< Person > results = null;
//        ResultSet resultSet = null;
//
//        try
//        {
//            // executeQuery returns ResultSet containing matching entries
//            resultSet = selectAllPeople.executeQuery();
//            results = new ArrayList< Person >();
//
//            while (resultSet.next())
//            {
//                results.add(new Person(
//                        resultSet.getInt("addressID"),
//                        resultSet.getString("firstName"),
//                        resultSet.getString("lastName"),
//                        resultSet.getString("email"),
//                        resultSet.getString("phoneNumber")));
//            }
//        }
//        catch (SQLException sqlException)
//        {
//            sqlException.printStackTrace();
//        }
//        finally
//        {
//            try
//            {
//                resultSet.close();
//            }
//            catch (SQLException sqlException)
//            {
//                sqlException.printStackTrace();
//                close();
//            }
//        }
//
//        return results;
//    }
//
//    // select person by last name
//    public List< Person > getPeopleByLastName(String name)
//    {
//        List< Person > results = null;
//        ResultSet resultSet = null;
//
//        try
//        {
//            selectPeopleByLastName.setString(1, name); // specify last name
//
//            // executeQuery returns ResultSet containing matching entries
//            resultSet = selectPeopleByLastName.executeQuery();
//
//            results = new ArrayList< Person >();
//
//            while (resultSet.next())
//            {
//                results.add(new Person(resultSet.getInt("addressID"),
//                        resultSet.getString("firstName"),
//                        resultSet.getString("lastName"),
//                        resultSet.getString("email"),
//                        resultSet.getString("phoneNumber")));
//            }
//        }
//        catch (SQLException sqlException)
//        {
//            sqlException.printStackTrace();
//        }
//        finally
//        {
//            try
//            {
//                resultSet.close();
//            }
//            catch (SQLException sqlException)
//            {
//                sqlException.printStackTrace();
//                close();
//            }
//        }
//
//        return results;
//    }
//
//    // add an entry
//    public int addPerson(
//            String fname, String lname, String email, String num)
//    {
//        int result = 0;
//
//        // set parameters, then execute insertNewPerson
//        try
//        {
//            insertNewPerson.setString(1, fname);
//            insertNewPerson.setString(2, lname);
//            insertNewPerson.setString(3, email);
//            insertNewPerson.setString(4, num);
//
//            // insert the new entry; returns # of rows updated
//            result = insertNewPerson.executeUpdate();
//        }
//        catch (SQLException sqlException)
//        {
//            sqlException.printStackTrace();
//            close();
//        }
//
//        return result;
//    }
//
//    // close the database connection
//    public void close()
//    {
//        try
//        {
//            connection.close();
//        }
//        catch (SQLException sqlException)
//        {
//            sqlException.printStackTrace();
//        }
//    }
//} // end class PersonQueries
//
//


public class Demo {
    static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    // jdbc Connection
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;
    private static final String URL = "jdbc:derby:fdb;create=true;";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";
    public static void main(String[] args)
    {
        createConnection();
        selectRestaurants();
        shutdown();
    }
///////////////////////////////////////////
    private static void createConnection()
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
    }

    private static void selectRestaurants()
    {
        try
        {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("SELECT * FROM ADMIN.BOOKS");
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i=1; i<=numberCols; i++)
            {
                //print Column Names
                System.out.print(rsmd.getColumnLabel(i)+"\t\t");
            }

            System.out.println("\n-------------------------------------------------");

            while(results.next())
            {
                int id = results.getInt(1);
                String restName = results.getString(2);
                String cityName = results.getString(3);
                System.out.println(id + "\t\t" + restName + "\t\t" + cityName);
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }

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

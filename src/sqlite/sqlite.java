/**
 * This class depends on sqlite-jdbc library from xerial.org, licensed under
 * Apache License version 2.0
 */
package sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.util.Date;
import java.io.File;


public class sqlite {

    private Connection connection;
    
    public sqlite() throws ClassNotFoundException {
        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");
    }
    
    public void sqlite_open(String dbName)
    {
        connection = null;
        try
        {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("CREATE TABLE access (tag TEXT, allow INTEGER, time TEXT, UNIQUE(tag) )");
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }
    
    public void sqlite_close()
    {
        try
        {
            if(connection != null)
                connection.close();
        }
        catch(SQLException e)
        {
            System.err.println(e);
        }
    }
    
    
    public void registerTag(String tag)
    {
        try
        {
            java.util.Date date = new java.util.Date();
            Timestamp stamp = new Timestamp(date.getTime());
            
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("INSERT INTO access VALUES ('"+ tag + "',0,'"+ stamp.toString()+ "')");
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }
    
    public void removeTag(String tag)
    {
        try
        { 
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("DELETE FROM access WHERE tag='"+ tag + "'");
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }
    
    public void addTagAccess(String tag)
    {
        try
        { 
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("UPDATE access set allow=1 WHERE tag='"+ tag + "'");
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }
    
        public void removeTagAccess(String tag)
    {
        try
        { 
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("UPDATE access set allow=0 WHERE tag='"+ tag + "'");
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }
}

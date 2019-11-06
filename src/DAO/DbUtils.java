/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author trung
 */
public class DbUtils {
    
    private String dbClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // SQL
    
    // private String dbClass =  "com.mysql.jdbc.Driver"; // My SQL
    private String dbName = "ProjectLTM";
    private String host =  "127.0.0.1";
    String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=ProjectLTM";
    private int port = 1433; // 1521 //My SQL: 3306
//    private String oracleURL ="jdbc:oracle:thin:@"+host+":"+port+":"+dbName; 
//    private String sybaseURL ="jdbc:sybase:Tds:" + host + ":" + port + ":" + "?SERVICENAME=" + dbName; 
//    private String mysqlURL ="jdbc:mysql://"+host +":"+ port + "/" +dbName; 
    private String sqlServerURL = "jdbc:sqlserver://"+ host +":"+port+";" + "DatabaseName=" + dbName + ";";
    
    private String userName = "sa";
    private String passWord = "12101998";
    Connection connection = null;
    
    public DbUtils() {System.out.println(sqlServerURL);}
    
    public DbUtils(String dbName) {
        
        this.dbName = "ProjectLTM";
        this.sqlServerURL = "jdbc:sqlserver://"+ host +":"+port+";" + "DatabaseName=" + this.dbName + ";" ;
        System.out.println(sqlServerURL);
    }

    public Connection getConnection()
    {
        try {
            // nap Driver
            Class.forName(dbClass);
               
            // thiết lập kết nối
            connection = DriverManager.getConnection(sqlServerURL, userName, passWord);
            if (connection!= null) System.out.println("Kết nối thành công!");  
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
                System.out.println("Kết nối không thành công!");
                Logger.getLogger(DbUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return connection;
        
    }
    public static void closeConnection(Connection connection)
    {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ResultSet executeQuery(Connection connection, String command)
    {
        ResultSet resultSet = null;
        try {
            Statement preparedStatement =  connection.createStatement();

            resultSet = preparedStatement.executeQuery(command);
            
            System.out.println("Thực hiện query thành công!");
            
//            preparedStatement.close();
            

        } catch (SQLException ex) {
            
            System.out.println("Không thể thực hiện query!");
            // Logger.getLogger(DbUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultSet;

    }
    
}

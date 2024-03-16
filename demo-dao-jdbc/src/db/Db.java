/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexp
 */
public class Db {
    
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //Driver class
            Connection conn; // Use java.sql.Connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "12345678"); // Database | URL , usr, pwd
            return conn;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
   
    public static void closeStatement(Statement st){
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
    public static void closeResultSet(ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
}

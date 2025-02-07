/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBaseConnection;

import java.sql.Connection;
import java.util.logging.Level;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

        
public class ConnectMySQL {
    
    public static void main(String[] args){
    
    try {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL JDBC Driver
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ead", "root", ""); // Database URL, Username, Password
        System.out.println("Database Connected Successfully!");
        
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(ConnectMySQL.class.getName()).log(Level.SEVERE, null, ex);
        
    } catch (SQLException ex){
        Logger.getLogger(ConnectMySQL.class.getName()).log(Level.SEVERE, null, ex);
    }
       
    }

    public static com.sun.jdi.connect.spi.Connection connection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

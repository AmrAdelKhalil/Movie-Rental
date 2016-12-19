/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amr
 */
public class DBC {
    
    private static final String DBNAME = "movie_rental";
    private static final String PASSWORD= "root";
    public DBC(){
        //   
    }
    private static Connection connection = null;
 
    public static Connection getActiveConnection() {

            try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connection = DriverManager
                                    .getConnection("jdbc:mysql://localhost:3306/"+DBNAME , 
                                                    "root" , PASSWORD);
                    return connection;
            } catch (ClassNotFoundException e) {
                    e.printStackTrace();
            } catch (SQLException e) {
                    e.printStackTrace();
            }
            return null;
    }
    public static void closeConnection(){
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

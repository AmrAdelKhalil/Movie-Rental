/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import DBConnection.DBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alaa
 */
public class addMovieModel {
    Connection connection=new DBC().getActiveConnection();
    int movieID=0;
    public void addMovie(HashMap<String,String>values){

        try {
            
            String query="insert into movie (name,description,rate_sum,rate_count,rate,img_url,duration,"+
                    "renting_price_per_day,category) values(?,?,0,0,0,?,?,?,?)";            
            PreparedStatement stmt=connection.prepareStatement(query);
            stmt.setString(1, values.get("movieName"));
            stmt.setString(2, values.get("description"));
            stmt.setString(3, "http://www.foxmovies.com/movies/x-men-apocalypse");
            stmt.setString(4, values.get("duration"));
            stmt.setString(5, values.get("price"));
            stmt.setString(6, values.get("category"));
            stmt.executeUpdate();
            
            query="SELECT LAST_INSERT_ID()";
            stmt=connection.prepareStatement(query);
            ResultSet res=stmt.executeQuery();
            res.next();
            movieID=res.getInt(1);
        
        } catch (SQLException ex) {
            Logger.getLogger(addMovieModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addMovieStaff(HashMap<String,String>movieStaff,int number)
    {   
        
        String query="insert into staff_member (name,role) values(?,?)",
                query1="SELECT LAST_INSERT_ID()",
                query2="insert into movie_staff (idMovie,idStaff) values(?,?)";
        int lastMember;
        ResultSet res;
        for(int i=0;i<number;i++)
        {
            try {
                PreparedStatement stmt=connection.prepareStatement(query);
                stmt.setString(1, movieStaff.get("name"+i));
                stmt.setString(2, movieStaff.get("role"+i));
                stmt.executeUpdate();
                
                stmt=connection.prepareStatement(query1);
                res=stmt.executeQuery();
                res.next();
                lastMember=res.getInt(1);
                stmt=connection.prepareStatement(query2);
                stmt.setInt(1, movieID);
                stmt.setInt(2, lastMember);
                stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(addMovieModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
}

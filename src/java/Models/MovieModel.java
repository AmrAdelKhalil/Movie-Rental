package Models;

import DBConnection.DBC;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MovieModel {
      int movieID=0;
    public MovieModel(){
        
    }
    
    public HashMap<String,String> showMovie(int id){
        
        HashMap<String,String> movie = new HashMap<>();
        String name="", description="", img_url="", duration="", renting_price_per_day="", category="";
        double rate_sum = 0.0, rate = 0.0, rate_count = 0.0;
        
        
        Connection con = DBC.getActiveConnection();
        String query="Select * from Movie where id=?";
        try {
            PreparedStatement p = (PreparedStatement) con.prepareStatement(query);
            p.setInt(1, id);
            ResultSet row = p.executeQuery();
            
            while(row.next()){
                name = row.getString("name");
                description = row.getString("description");
                img_url = row.getString("img_url");
                duration = row.getString("duration");
                renting_price_per_day = row.getString("renting_price_per_day");
                category = row.getString("category");
                rate_sum = row.getDouble("rate_sum");
                rate = row.getDouble("rate");
                rate_count = row.getDouble("rate_count");
            }
            
            movie.put("name", name);
            movie.put("description", description);
            movie.put("img_url", img_url);
            movie.put("duration", duration);
            movie.put("renting_price_per_day", renting_price_per_day);
            movie.put("category", category);
            movie.put("rate_sum", String.valueOf(rate_sum));
            movie.put("rate", String.valueOf(rate));
            movie.put("rate_count", String.valueOf(rate_count));
            return movie;
        } catch (SQLException ex) {
            Logger.getLogger(MovieModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DBC.closeConnection();
        
        return null;
    }
    
    public void addMovie(HashMap<String,String>values) throws SQLException{
         Connection connection=new DBC().getActiveConnection();
            
        String query="insert into movie (name,description,rate_sum,rate_count,rate,img_url,duration,"+
                "renting_price_per_day,category) values(?,?,0,0,0,?,?,?,?)";            
        java.sql.PreparedStatement stmt=connection.prepareStatement(query);
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
        
        connection.close();
    }
    
    public void addMovieStaff(HashMap<String,String>movieStaff,int number) throws SQLException
    {   
        Connection connection=new DBC().getActiveConnection();
        String query="insert into staff_member (name,role) values(?,?)",
                query1="SELECT LAST_INSERT_ID()",
                query2="insert into movie_staff (idMovie,idStaff) values(?,?)";
        int lastMember;
        ResultSet res;
        for(int i=0;i<number;i++)
        {
            java.sql.PreparedStatement stmt=connection.prepareStatement(query);
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
        }
        connection.close();
    }
    
}

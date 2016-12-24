package Models;

import DBConnection.DBC;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MovieModel {
    static int movieID=0;
    HashMap<String,String>currDataMovie=new  HashMap<String,String>();
    static public String queryMovie="";
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
         Connection connection= DBC.getActiveConnection();
            
        String query="insert into movie (name,description,rate_sum,rate_count,rate,img_url,duration,"+
                "renting_price_per_day,category,year,quality) values(?,?,0,0,0,?,?,?,?,?,?)";            
        PreparedStatement stmt=connection.prepareStatement(query);
        stmt.setString(1, values.get("movieName"));
        stmt.setString(2, values.get("description"));
        stmt.setString(3, "/Movie-Rental/images/movie6.jpg");
        stmt.setString(4, values.get("duration"));
        stmt.setString(5, values.get("price"));
        stmt.setString(6, values.get("category"));
        stmt.setString(7, values.get("year"));
        stmt.setString(8, values.get("quality"));
        stmt.executeUpdate();

        query="SELECT LAST_INSERT_ID()";
        stmt=connection.prepareStatement(query);
        ResultSet res=stmt.executeQuery();
        res.next();
        movieID=res.getInt(1);
        
        DBC.closeConnection();
    }
    
    public void addMovieStaff(HashMap<String,String>movieStaff,int number) throws SQLException
    {   
        Connection connection= DBC.getActiveConnection();
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
        DBC.closeConnection();
    }
    public void seqQuerySearch(HashMap<String,String>values){
        
        String name="",quality="",category="",rating="",order="";
        boolean prev=false;
        
        if(values.get("name").equals("")==false)
        {
            name="name like '%"+values.get("name")+"%' ";
            prev=true;
        }
        
        if(values.get("quality").equals("all")==false)
        {
            quality=(prev==true?" and":"")+ " quality like '%"+values.get("quality")+"%' ";
            prev=true;
        }
        
        if(values.get("category").equals("all")==false)
        {
            category=(prev==true?" and":"")+ " category like '%"+values.get("category")+"%' ";
            prev=true;
        }
        if(values.get("rating").equals("all")==false)
        {
            rating=(prev==true?" and":"")+ " rate >= "+values.get("rating");
            prev=true;
        }
        if(values.get("order_by").equals("all")==false)
            order=" order by "+values.get("order_by")+(values.get("order_by").equals("name")?"":" DESC");
        
        queryMovie="select * from movie where "+name+quality+category+rating+order;
    
    }
    
    public ArrayList<HashMap<String,String> > returnMovies()
    {  
       ArrayList< HashMap<String,String> >result=new ArrayList<HashMap<String,String> >();
       Connection connection =  DBC.getActiveConnection();
       
       try {
           if(queryMovie.equals(""))
                queryMovie="select * from movie order by year desc";
            PreparedStatement stmt=connection.prepareStatement(queryMovie);
            ResultSet res=stmt.executeQuery();
          
            
            while(res.next())
            {
                HashMap<String,String>curr=new HashMap<String,String>();
                
                curr.put("name",res.getString(2));
                curr.put("rate",res.getString(6));
                curr.put("img",res.getString(7));
                result.add(curr);
              
            }
             DBC.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MovieModel.class.getName()).log(Level.SEVERE, null, ex);
        
        }
       
         DBC.closeConnection();
        queryMovie="";
        return result;
   }
   
    public HashMap<String,String> getValues(String idMovie)
    {   
        movieID=Integer.parseInt(idMovie);
        
        HashMap<String,String>result=new HashMap<String,String>();
        Connection connection=DBC.getActiveConnection();
        queryMovie="select * from movie where id="+idMovie;
             System.out.println(queryMovie);
        try {
             PreparedStatement stmt = connection.prepareStatement(queryMovie);
             ResultSet res = stmt.executeQuery();
             res.next();
             System.out.println(res.getString("quality"));
             result.put("name", "'" + res.getString("name") + "'" );
             result.put("category", "'" + res.getString("category") + "'" );
             result.put("description", "'" + res.getString("description") + "'" );
             result.put("duration",res.getString("duration"));
             result.put("price",res.getString("renting_price_per_day"));
             result.put("year",res.getString("year"));
             result.put(res.getString("quality"),"selected");
             result.put("quality",res.getString("quality"));
             queryMovie="select idStaff from movie_staff where idMovie="+idMovie;
             stmt = connection.prepareStatement(queryMovie);
             res = stmt.executeQuery();
             Integer curr=0;
         
             while(res.next())
             {
                queryMovie="select name,role from staff_member where id="+res.getInt("idStaff");
                stmt = connection.prepareStatement(queryMovie);
                ResultSet res1 = stmt.executeQuery();
                res1.next();
                result.put("name"+curr.toString(),"'" + res1.getString("name") + "'" );
                result.put("role"+curr.toString(),"'" + res1.getString("role") + "'" );
                 System.out.println(result.get("name0"));
                curr++;
             }
             
             result.put("number",curr.toString());
            DBC.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MovieModel.class.getName()).log(Level.SEVERE, null, ex);
        }
       DBC.closeConnection();
        currDataMovie=result;
        queryMovie="";
        return result;
    }
    
    public void updateMovie(HashMap<String,String>values)
    {   
        
            String name="",quality="",category="",duration="",year="",price="",description="";
            boolean prev=false;
            if(values.get("name").equals(currDataMovie.get("name"))==false)
            {
                name="name='"+values.get("name")+"'";
                prev=true;
            }   if(values.get("category").equals(currDataMovie.get("category"))==false)
            {
                category=(prev==true?" ,":"")+" category='"+values.get("category")+"'";
                prev=true;
            }   if(values.get("description").equals(currDataMovie.get("description"))==false)
            {
                description=(prev==true?" , ":"")+" description='"+values.get("description")+"'";
                prev=true;
            }   if(values.get("duration").equals(currDataMovie.get("duration"))==false)
            {
                duration=(prev==true?" ,":"")+" duration="+values.get("duration");
                prev=true;
            }   if(values.get("price").equals(currDataMovie.get("price"))==false)
            {
                price=(prev==true?" , ":"")+" renting_price_per_day="+values.get("price");
                prev=true;
            }   if(values.get("year").equals(currDataMovie.get("year"))==false)
            {
                year=(prev==true?" , ":"")+" year="+values.get("year");
                prev=true;
            }   if(values.get("quality").equals(currDataMovie.get("quality"))==false)
            {
                quality=(prev==true?" , ":"")+" quality='"+values.get("quality")+"'";
                prev=true;
            }  
            queryMovie="update movie set "+name+category+description+duration+
                    price+year+quality+" where id="+((Integer)movieID).toString();
       try { 
            Connection con = DBC.getActiveConnection();
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(queryMovie);
            stmt.executeUpdate();
            DBC.closeConnection();
       } catch (SQLException ex) {
            Logger.getLogger(MovieModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     DBC.closeConnection();
    }
}

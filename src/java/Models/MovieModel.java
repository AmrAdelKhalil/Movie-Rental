package Models;

import DBConnection.DBC;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MovieModel {
    static int movieID=0;
   
    HashMap<String,String>currDataMovie=new  HashMap<String,String>();
    
    ArrayList<Integer>actrosId=new ArrayList();
    
    static public String queryMovie="";
    
    public MovieModel(){
        
    }
    
    public HashMap<String,String> showMovie(int id, int userId){
        
        HashMap<String,String> movie = new HashMap<>();
        String name="", description="", img_url="", duration="", renting_price_per_day="", category="", year="", quality="";
        double rate_sum = 0.0, rate = 0.0, rate_count = 0.0;
        boolean isRent = true, currentRent= false;
        Date endDate,startDate; 
        Connection con = DBC.getActiveConnection();
        String query="Select * from movie where id=?";
        try {
            PreparedStatement p = (PreparedStatement) con.prepareStatement(query);
            p.setInt(1, id);
            ResultSet row = p.executeQuery();
            
            if(row.next()){
                name = row.getString("name");
                description = row.getString("description");
                img_url = row.getString("img_url");
                duration = row.getString("duration");
                renting_price_per_day = row.getString("renting_price_per_day");
                category = row.getString("category");
                rate_sum = row.getDouble("rate_sum");
                rate = row.getDouble("rate");
                rate_count = row.getDouble("rate_count");
                year = row.getString("year");
                quality = row.getString("quality");
            }
            
            if (userId != -1){
            query="Select * from movie_user_rent where idUser=? and idMovie=?";
            p = (PreparedStatement) con.prepareStatement(query);
            p.setInt(1, userId);
            p.setInt(2, id);
            
            row = p.executeQuery();
            
            if(row.next()){
                endDate = row.getDate("endDate");
                startDate = row.getDate("startDate");
                java.util.Date utilDate = new java.util.Date();
                Date currentDate = new Date(utilDate.getTime());
                if (currentDate.getTime() > endDate.getTime()){
                    long diff = currentDate.getTime() - endDate.getTime();
                    long diffDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                    if (diffDays < 7 ){
                        isRent = false;
                    }
                }else{
                    currentRent = true;
                    movie.put("startDate", String.valueOf(startDate));
                    movie.put("endDate", String.valueOf(endDate));
                }
            }
        }else{
            isRent = false;
            currentRent = true;
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
            movie.put("id", String.valueOf(id));
            movie.put("year", year);
            movie.put("quality", quality);
            movie.put("isRent", String.valueOf(isRent));
            movie.put("currentRent", String.valueOf(currentRent));
            DBC.closeConnection();

            return movie;
        } catch (SQLException ex) {
            Logger.getLogger(MovieModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DBC.closeConnection();
        
        return null;
    }
    
    public void addMovie(HashMap<String,String>values) {
       try{
        Connection connection= DBC.getActiveConnection();
            
        String query="insert into movie (name,description,rate_sum,rate_count,rate,img_url,duration,"+
                "renting_price_per_day,category,year,quality) values(?,?,0,0,0,?,?,?,?,?,?)";            
        PreparedStatement stmt=connection.prepareStatement(query);
        stmt.setString(1, values.get("movieName"));
        stmt.setString(2, values.get("description"));

        stmt.setString(3, values.get("imgUrl"));
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
    }catch (SQLException ex) {
            Logger.getLogger(MovieModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DBC.closeConnection();
        
 }
    public void addMovieStaff(HashMap<String,String>movieStaff,int number) 
    {   
        try{
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
       }catch (SQLException ex) {
            Logger.getLogger(MovieModel.class.getName()).log(Level.SEVERE, null, ex);
        
        }
       
         DBC.closeConnection();
    }
    
    public void seqQuerySearch(HashMap<String,String>values){
        
        String name="",quality="",category="",rating="",order="";
        boolean prev=false,isActor=false;
        String Movie=" movie where ",
               allTables="movie,movie_staff,staff_member where movie.id=movie_staff.idMovie and "+
                          "movie_staff.idStaff=staff_member.id and staff_member.";
        if(values.get("name").equals("")==false)
        {
            name="name like '%"+values.get("name")+"%' ";
            isActor=hasActor(values.get("name"));
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
        
        queryMovie="select * from " +(isActor?allTables:Movie)+name+quality+category+rating+order;
        
    }
    public boolean hasActor(String name)
    {   
         Connection connection =  DBC.getActiveConnection();
         try{
            queryMovie="select * from staff_member where name='"+name+"'";

            PreparedStatement stmt=connection.prepareStatement(queryMovie);
            ResultSet res=stmt.executeQuery();
             while(res.next()){
                 DBC.closeConnection();
                 queryMovie="";
                 return true;
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(MovieModel.class.getName()).log(Level.SEVERE, null, ex);
        
        }
       
        DBC.closeConnection();
        queryMovie="";
        return false;
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

                curr.put("id", res.getString("id"));
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

             result.put("imgUrl", "'" + res.getString("img_url") + "'" );
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
                queryMovie="select * from staff_member where id="+res.getInt("idStaff");
                stmt = connection.prepareStatement(queryMovie);
                ResultSet res1 = stmt.executeQuery();
                res1.next();
                result.put("id"+curr.toString(),"'" + res1.getString("id") + "'" );
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
            String name="",quality="",category="",duration="",year="",price="",description="",imgUrl="";
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
            }   if(values.get("imgUrl").equals(currDataMovie.get("imgUrl"))==false)
            {
                imgUrl=(prev==true?" , ":"")+" img_url='"+values.get("imgUrl")+"'";
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
            queryMovie="update movie set "+name+category+description+imgUrl+duration+
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
    public void updateStaff(HashMap<String,String>values,int num)
    {   
         Connection con = DBC.getActiveConnection();
        for(Integer i=0;i<num;i++)
        {  
            boolean prev=false;
            String name="",role="";
            if(values.get("name"+i.toString()).equals(currDataMovie.get("name"+i.toString()))==false)
            {   
                name="name='"+values.get("name"+i.toString())+"'";
                prev=true;
            }
            if(values.get("role"+i.toString()).equals(currDataMovie.get("role"+i.toString()))==false)
            {
                role=(prev==true?" ,":"")+" role='"+values.get("role"+i.toString())+"'";
               
            }
             queryMovie="update staff_member set "+name+role+" where id="+values.get("id"+i.toString());
       try { 
                System.out.println(queryMovie);
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(queryMovie);
            stmt.executeUpdate();
           
       } catch (SQLException ex) {
            Logger.getLogger(MovieModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        }
        queryMovie="";
        DBC.closeConnection();
    }
}

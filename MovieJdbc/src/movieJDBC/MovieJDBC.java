package movieJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MovieJDBC
{
	public static void connect() 
	{
        Connection conn = null;
        try
        {           
            String url = "jdbc:sqlite:C:/sqlite/indra.db";
            conn = DriverManager.getConnection(url);            
            System.out.println("Connection to SQLite has been established.");           
        } 
        
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        } 
        finally 
        {
            try
            {
                if (conn != null) 
                {
                    conn.close();
                }
            } 
            catch (SQLException ex) 
            {
                System.out.println(ex.getMessage());
            }
        }
    }
	
	public static void insertdb()
	{
	      Connection c = null;
	        Statement stmt = null;
	        try 
	        {
	          Class.forName("org.sqlite.JDBC");
	          c = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/indra.db");
	          c.setAutoCommit(false);
	          System.out.println("Database opened successfully");
	 
	          stmt = c.createStatement();
	          String sql = "INSERT INTO Movies (Name,Actor,Actress,Director,YearofRelease) " +
	                       "VALUES ('Shershaah','Sidharth Malhorta','Kiara Advani','Vishnuvardhan',2021);";
	         
	          stmt.executeUpdate(sql);
	          stmt.close();
	          c.commit();
	          c.close();
	        }
	        catch ( Exception e )
	        {
	          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	          System.exit(0);
	        }
	        System.out.println("Records inserted successfully");
	  }
    
	public static void selectall()
	{
	        Connection c = null;
	        Statement stmt = null;
	        
	        try 
	        {
	          Class.forName("org.sqlite.JDBC");
	          c = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/indra.db");
	          c.setAutoCommit(false);
	          System.out.println("Database opened successfully");
	 
	          stmt = c.createStatement();
	          ResultSet rs = stmt.executeQuery( "Select * from Movies;" );
	          while ( rs.next() ) 
	          {
	             int Srno = rs.getInt("Srno");
	             String  Name = rs.getString("Name");
	             String  Actor = rs.getString("Actor");
	             String Actress = rs.getString("Actress");
	             String Director = rs.getString("Director");
	             String Year_of_Release = rs.getString("YearofRelease");
	             System.out.println( "Srno : " + Srno );
	             System.out.println( "Name : " + Name );
	             System.out.println( "Actor : " + Actor );
	             System.out.println( "Actress : " + Actress );
	             System.out.println( "Director : " + Director );
	             System.out.println( "Year of Release : " + Year_of_Release );
	             System.out.println();
	          }
	          rs.close();
	          stmt.close();
	          c.close();
	        } 
	        
	        catch ( Exception e ) 
	        {
	          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	          System.exit(0);
	        }
	        System.out.println("Executed successfully");  
	  }
	
	public static void selectname()
	{
	        Connection c = null;
	        Statement stmt = null;
	        
	        try 
	        {
	          Class.forName("org.sqlite.JDBC");
	          c = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/indra.db");
	          c.setAutoCommit(false);
	          System.out.println("Database opened successfully");
	          	          
	          System.out.println("Enter the Actor you want the movies of:- ");
	          Scanner sc = new Scanner(System.in);
	          
	          String Actorname = sc.nextLine();
	          stmt = c.createStatement();
	          ResultSet rs = stmt.executeQuery( "Select Name from Movies where Actor='" +Actorname+ "';" );
              
	          while ( rs.next() ) 
	          {	             
	             String  Name = rs.getString("Name");	             	          
	             System.out.println( "Name of the Movies: " + Name );	             	            
	          }
	          rs.close();
	          stmt.close();
	          c.close();
	        } 
	        
	        catch ( Exception e ) 
	        {
	          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	          System.exit(0);
	        }
	        System.out.println("Operation done successfully");  
	  }
	
	
    public static void main(String[] args)
    {
        connect();
        insertdb();
        selectall();
        selectname();
        
    }
}


package Server;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;


import javax.swing.table.DefaultTableModel;

public class database {
	Connection con = null;
	public static void main(String[] args)
	{
		database db = new database();
		db.connect();
	}
    
	public boolean connect()
	{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	//	String url = "jdbc:sqlite:" + classLoader.getResource("database.db"); 
		String url = "jdbc:sqlite:database.db";
		while(true) {
		try {
			con = DriverManager.getConnection(url);
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		}
	}
	
	public boolean deletestandings()
	{
		String sql = "DELETE FROM standings;";
		try {
			System.out.println(sql);
			Statement stmt = con.createStatement();
		    stmt.executeUpdate(sql);
		    stmt.close();
		    close();
		    connect();
		    stmt = con.createStatement();
		    stmt.executeUpdate("DELETE FROM submission");
		    stmt.close();
		    return true;
		}
		catch(Exception e)
		{
			
		}
		try {
			con.close();
			connect();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
		return false;
	}
	
	public boolean newstandings()
	{
		String sql = "INSERT INTO standings(Username) SELECT username from users;";
		try {
			System.out.println(sql);
			Statement stmt = con.createStatement();
		    stmt.executeUpdate(sql);
		    stmt.close();
		    
		    System.out.println("Done");
		    return true;
		}
		catch(Exception e)
		{
			
		}
		return false;
	}
	
	public boolean useradd(String user,String pass)
	{
		String sql = "INSERT INTO users VALUES('" + user +"','" + pass + "');";
		try {
			System.out.println(sql);
			Statement stmt = con.createStatement();
		    stmt.executeUpdate(sql);
		    System.out.println("User added");
		    stmt.close();
		    return true;
		}
		catch(Exception e)
		{
			
		}
		return false;
	}
	
	public boolean usercheck(String user,String pass)
	{
		
		String sql = "SELECT * FROM users WHERE username='"+ user+ "' AND password='"+pass+"';";
		System.out.println(sql);
		int c = 0;
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println(rs);
			while(rs.next())
				c++;
			System.out.println(c);
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		if(c==1)
			return true;
		return false;
	}
	
	public void submitentry(String user,String pname,String lang,String st,String t)
	{
		Calendar cal  = Calendar.getInstance();
		Date date = cal.getTime();
		DateFormat df = new SimpleDateFormat("hh:mm:ss dd:MM:yyyy");
		String datetime = df.format(date);
		System.out.println("checking  :" + user + " "+ pname + " " + lang);
		String sql = "INSERT INTO submission(username,problemname,language,S_Time,status,Time) VALUES('"+ user+"','"+pname+"','"+lang+"','"+ datetime + "','"+ st + "','"+ t + "');";
	
		System.out.println(sql);
		try {
			Statement stmt = con.createStatement();
		    stmt.executeUpdate(sql);
		    stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	public DefaultTableModel submission()
	{
		String sql = "SELECT * FROM submission ORDER BY No DESC;";
		System.out.println(sql);
		ResultSet rs = null;
		int c = 0;
		Statement stmt;
		try {
			System.out.print("Tring to get");
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			try {
	            ResultSetMetaData metaData = rs.getMetaData();
	            int numberOfColumns = metaData.getColumnCount();
	            Vector columnNames = new Vector();

	            // Get the column names
	            for (int column = 0; column < numberOfColumns; column++) {
	                columnNames.addElement(metaData.getColumnLabel(column + 1));
	            }

	            // Get all rows.
	            Vector rows = new Vector();

	            while (rs.next()) {
	                Vector newRow = new Vector();

	                for (int i = 1; i <= numberOfColumns; i++) {
	                    newRow.addElement(rs.getObject(i));
	                }

	                rows.addElement(newRow);
	            }
	            stmt.close();
	            return new DefaultTableModel(rows, columnNames);
	        } catch (Exception e) {
	            e.printStackTrace();
	            stmt.close();
	            return null;
	        }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return null;
	}
	public DefaultTableModel user()
	{
		String sql = "SELECT * FROM users;";
		System.out.println(sql);
		ResultSet rs = null;
		int c = 0;
		Statement stmt;
		try {
			System.out.print("Tring to get");
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			try {
	            ResultSetMetaData metaData = rs.getMetaData();
	            int numberOfColumns = metaData.getColumnCount();
	            Vector columnNames = new Vector();

	            // Get the column names
	            for (int column = 0; column < numberOfColumns; column++) {
	                columnNames.addElement(metaData.getColumnLabel(column + 1));
	            }

	            // Get all rows.
	            Vector rows = new Vector();

	            while (rs.next()) {
	                Vector newRow = new Vector();

	                for (int i = 1; i <= numberOfColumns; i++) {
	                    newRow.addElement(rs.getObject(i));
	                }

	                rows.addElement(newRow);
	            }
	            stmt.close();
	            return new DefaultTableModel(rows, columnNames);
	        } catch (Exception e) {
	            e.printStackTrace();
	            stmt.close();
	            return null;
	        }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return null;
	}
	
	public DefaultTableModel standings()
	{
		String sql = "SELECT * FROM standings ORDER BY Solved DESC,Penalty ASC;";
		System.out.println(sql);
		ResultSet rs = null;
		int c = 0;
		Statement stmt;
		try {
			System.out.print("Tring to get");
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			try {
	            ResultSetMetaData metaData = rs.getMetaData();
	            int numberOfColumns = metaData.getColumnCount();
	            Vector columnNames = new Vector();
	            columnNames.addElement("Rank");

	            // Get the column names
	            for (int column = 0; column < numberOfColumns; column++) {
	                columnNames.addElement(metaData.getColumnLabel(column + 1));
	            }

	            // Get all rows.
	            Vector rows = new Vector();
                int r =1;
	            while (rs.next()) {
	                Vector newRow = new Vector();
	                String s = ""+ r;
	                r++;
	                newRow.addElement(s);
	                for (int i = 1; i <= numberOfColumns; i++) {
	                    newRow.addElement(rs.getObject(i));
	                }

	                rows.addElement(newRow);
	            }
	            stmt.close();
	            return new DefaultTableModel(rows, columnNames);
	        } catch (Exception e) {
	            e.printStackTrace();
	            stmt.close();
	            return null;
	        }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return null;
	}
	
	public void rating_change(String user,boolean f,int t,String p) throws SQLException
	{
		connect();
		String str = "AND (" + p + " IS NULL OR " + p + "<=0)";
		
		if(f)
		{
			try {
				String Sql = "UPDATE standings SET Solved = Solved + 1 where Username = '" + user + "' " +str;
				Statement stmt = con.createStatement();
			    stmt.executeUpdate(Sql);
			    stmt.close();
			   
			    try {
			    	close();
				    connect();
				    Sql = "UPDATE standings SET Penalty = (Penalty - " +  p + "*10 + " + t +") where Username = '" + user + "' " + str;
					stmt = con.createStatement();
				    stmt.executeUpdate(Sql);
				    stmt.close();
			    }
			    catch(Exception ex)
			    {
			        close();
				    connect();
			    	Sql = "UPDATE standings SET Penalty = Penalty + " + t +" where Username = '" + user + "' "+ str;
					stmt = con.createStatement();
				    stmt.executeUpdate(Sql);
				    stmt.close();
			    }
			    try {
			    	close();
				    connect();
				    Sql = "UPDATE standings SET " + p + " = 1-" + p +" where Username = '" + user + "' and " + p +" IS NOT NULL and " + p +" <= 0";
				  //  JOptionPane.showMessageDialog(null, Sql);
					stmt = con.createStatement();
				    stmt.executeUpdate(Sql);
				    stmt.close();
				    close();
				    connect();
				    Sql = "UPDATE standings SET " + p + " = +1 where Username = '" + user + "'and " + p +" IS NULL";
					stmt = con.createStatement();
				    stmt.executeUpdate(Sql);
				    stmt.close();
			    }
			    catch(Exception ex)
			    {
			        
			    }
			}
			catch(Exception e)
			{
				
			}
		}
		else
		{
			String Sql;
			Statement stmt;
			try {
				 Sql = "UPDATE standings SET " + p + " = " + p +"-1 where Username = '" + user + "' and " + p +" IS NOT NULL  and " + p +" <= 0";
				//    JOptionPane.showMessageDialog(null, Sql);
					stmt = con.createStatement();
				    stmt.executeUpdate(Sql);
				    stmt.close();
				    close();
				    connect();
				    Sql = "UPDATE standings SET " + p + " = -1 where Username = '" + user + "'and " + p +" IS NULL";
					stmt = con.createStatement();
				    stmt.executeUpdate(Sql);
				    stmt.close();
		    }
		    catch(Exception ex)
		    {
		       
		    }
		}
		close();
		
	}
	
	public void close(){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

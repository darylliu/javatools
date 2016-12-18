package com.database.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;   

public class DbUtil {
	private static Connection con=null;
	private static DatabaseMetaData dbMetaData = null; 
	
	private static String user = "root";
	private static String password = "123456";
	private static String database = "NimrodHome";
	private static String remarks = "true";
	private static String useInformationSchema = "true";
	
	private static Map<String, String> tableNames = null;
	private static Map<String, String> tableRemarks = null;
	
	static
  {   
      try  
      {   
          if (dbMetaData == null)   
          {   
          		Properties props =new Properties();
              Class.forName("com.mysql.jdbc.Driver");   
              String url = "jdbc:mysql://localhost:3306/"+database+"?useUnicode=true&characterEncoding=utf8&useSSL=false"; 
              props.setProperty("user", user);
              props.setProperty("password", password);
              props.setProperty("remarks", remarks);
              props.setProperty("useInformationSchema", useInformationSchema);
              con = DriverManager.getConnection(url, props);   
              dbMetaData = con.getMetaData();   
          }   
      } catch (ClassNotFoundException e)   
      {   
          e.printStackTrace();   
      } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
  }  
	
	public static Connection getConnection(){
		return con;
	}
	
	
	/**
	 * get all names of tables  <table，comment>
	 * @return
	 */
	public static Map<String, String> getAllTableNames()   
  {   
      try  
      {   
      		if(tableNames != null)
      			return tableNames;
      		tableNames = new HashMap<String, String>();
          // table type. Typical types are "TABLE", "VIEW", "SYSTEM   
          // TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS",   
          // "SYNONYM".   
          String[] types = { "TABLE" };   
          ResultSet rs = dbMetaData.getTables(null, user, "%", types);   
          while (rs.next())   
          {   
              String tableName = rs.getString("TABLE_NAME");   
              String remarks = rs.getString("REMARKS");  
              System.out.println(rs.getString("REMARKS"));
              System.out.println(rs.getString("TABLE_TYPE"));
              System.out.println(rs.getString("TYPE_SCHEM"));
              tableNames.put(tableName, remarks);
          }   
          return tableNames;
      } catch (SQLException e)   
      {   
          e.printStackTrace(); 
          return null;
      }   
  }  
	
	/**
	 * get all comments of tables  <comment，table>
	 * @return
	 */
	public static Map<String, String> getAllTableRemarks()   
  {   
      try  
      {   
      		if(tableRemarks != null)
      			return tableRemarks;
      		tableRemarks = new HashMap<String, String>();
          // table type. Typical types are "TABLE", "VIEW", "SYSTEM   
          // TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS",   
          // "SYNONYM".   
          String[] types = { "TABLE" };   
          ResultSet rs = dbMetaData.getTables(null, user, "%", types);   
          while (rs.next())   
          {   
              String tableName = rs.getString("TABLE_NAME");   
              String remarks = rs.getString("REMARKS");  
              
              
              tableNames.put(remarks, tableName);
              System.out.println(tableName + "-" + remarks);   
          }   
          return tableRemarks;
      } catch (SQLException e)   
      {   
          e.printStackTrace(); 
          return null;
      }   
  } 
	
	/**
	 * get columns of the target table  <column, comment>
	 * @param tableName
	 * @return
	 */
	public static Map<String, String> getAllColumnNames(String tableName)   
  {   
      try  
      {   
      	  Map<String, String> columnNames = new HashMap<String, String>();
          ResultSet rs = dbMetaData.getColumns(null, user, tableName, null);
          while (rs.next())   
          {   
              String columnName = rs.getString("COLUMN_NAME");   
              String remarks = rs.getString("REMARKS");  
              columnNames.put(columnName, remarks);
          }   
          return columnNames;
      } catch (SQLException e)   
      {   
          e.printStackTrace(); 
          return null;
      }   
  }
	
	/**
	 * get comments of the target table  <comment, column>
	 * @param tableName
	 * @return
	 */
	public static Map<String, String> getAllColumnRemarks(String tableName)   
  {   
      try  
      {   
      	  Map<String, String> columnRemarks = new HashMap<String, String>();
          ResultSet rs = dbMetaData.getColumns(null, user, tableName, null);
          while (rs.next())   
          {   
              String columnName = rs.getString("COLUMN_NAME");   
              String remarks = rs.getString("REMARKS");  
              columnRemarks.put(remarks, columnName);
          }   
          return columnRemarks;
      } catch (SQLException e)   
      {   
          e.printStackTrace(); 
          return null;
      }   
  }
	
	

}

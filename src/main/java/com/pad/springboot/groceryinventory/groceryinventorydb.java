package com.pad.springboot.groceryinventory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import com.mysql.cj.xdevapi.Statement;

public class groceryinventorydb {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		try {
			
			String sql = "select item_name,quantity,user_name from inventory.item ;";
			System.out.println(sql);
			String url = "jdbc:mysql://localhost:3306/?user=root";
			String username = "root";
			String password = "$r33p@!)";
			Connection con = DriverManager.getConnection(url,username,password);
			java.sql.Statement st = con.createStatement();
			ResultSet resultSet = st.executeQuery(sql);
			
			if (con != null) {
			 while (resultSet.next()) { 
                 String item_name = resultSet.getString("item_name"); 
                 int qty = Integer.parseInt(resultSet.getString("quantity")); 
                 String user_name = resultSet.getString("user_name"); 

                 System.out.println(item_name+" "+qty+" "+user_name); 
                  }  
			 }                   
			 else {
				 System.out.println("Connection has been lost");
			 }
			
			
			con.close();		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	@SuppressWarnings("unused")
	public static Boolean updateinventory(String item, int quantity, String name) {
		
    try {    
	        String sql = "	update inventory.item set quantity = "+quantity+", user_name = '"+name.trim()+"' where item_name='"+item.trim()+"';";
			System.out.println(sql);
	        String url = "jdbc:mysql://localhost:3306/?user=root";
			String username = "root";
			String password = "$r33p@!)";
			Connection con = DriverManager.getConnection(url,username,password);
			java.sql.Statement st = con.createStatement();
			int rowsAffected = st.executeUpdate(sql);;
			
			
			if (con != null) {
				System.out.println("Updated Successfully");
				System.out.println(" rowsAffected "+rowsAffected);
				 
			} 
			else { System.out.println("Connection has been lost"); 
			         return false; 
			}
			
			  con.close();				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	
	
	@SuppressWarnings("unused")
	public static LinkedHashMap<String,LinkedHashMap<Integer,String>> getStockReport() {
		
		/*
		 * LinkedHashMap<String,LinkedHashMap<Integer,String>> map = new
		 * LinkedHashMap<String,LinkedHashMap<Integer,String>>();
		 */
		LinkedHashMap<String, LinkedHashMap<Integer, String>> outerMap = new LinkedHashMap<>();
		
	    try {  
	    	
		        String sql = "select item_name,quantity,user_name from inventory.item ;";
		        System.out.println(sql);
		        String url = "jdbc:mysql://localhost:3306/?user=root";
				String username = "root";
				String password = "$r33p@!)";
				Connection con = DriverManager.getConnection(url,username,password);
				java.sql.Statement st = con.createStatement();
				ResultSet resultSet = st.executeQuery(sql);
				
				if (con != null) {
					 while (resultSet.next()) { 
		                 String item_name = resultSet.getString("item_name"); 
		                 int qty = Integer.parseInt(resultSet.getString("quantity")); 
		                 String user_name = resultSet.getString("user_name"); 

		                 System.out.println(item_name+" "+qty+" "+user_name); 
		                 
		                 
		                 String outerKey = resultSet.getString("item_name"); 
		                 int innerKey = Integer.parseInt(resultSet.getString("quantity")); 
		                 String innerValue = resultSet.getString("user_name"); 
		                 // Check if the outer key already exists in the outer map
		                 LinkedHashMap<Integer, String> innerMap = outerMap.get(outerKey);
		                 if (innerMap == null) {
		                     innerMap = new LinkedHashMap<>();
		                     outerMap.put(outerKey, innerMap);
		                 }

		                 // Insert the inner key-value pair into the inner map
		                 innerMap.put(innerKey, innerValue);
		                 
		                 
		                  }  
					 }                   
					 else {
						 System.out.println("Connection has been lost");
					 }
                
				 
				
							
				  con.close();	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return outerMap;
		
	}






	@SuppressWarnings("unused")
	public static boolean findItem(String item) {
		// TODO Auto-generated method stub
		int rowsFetched=0;
		try {    
	        String sql = "	select item_id from inventory.item where item_name='"+item.trim()+"';";
			System.out.println(sql);
	        String url = "jdbc:mysql://localhost:3306/?user=root";
			String username = "root";
			String password = "$r33p@!)";
			Connection con = DriverManager.getConnection(url,username,password);
			java.sql.Statement st = con.createStatement();
			ResultSet resultSet = st.executeQuery(sql);
			
			
			if (con != null) {
				while (resultSet.next()) { 
	                 // int rowsFetched1 = Integer.parseInt(resultSet.getInt("item_id"));
	                   int innerKey = Integer.parseInt(resultSet.getString("item_id")); 
	                   rowsFetched=innerKey;

	                 System.out.println(" Checking the item "+item+" item_id "+rowsFetched); 
				 }
	        
			} else { 
				System.out.println("Connection has been lost");    
			}
			
			  con.close();				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(rowsFetched>0)
			return true;
		else
		    return false;
	}






	@SuppressWarnings("unused")
	public static void insertItem(String item) {
		// TODO Auto-generated method stub
		
		 try {    
		        String sql = "	insert into inventory.item(item_name,quantity,user_name) values('"+item.trim()+"',0,'default'); ";
				System.out.println(sql);
		        String url = "jdbc:mysql://localhost:3306/?user=root";
				String username = "root";
				String password = "$r33p@!)";
				Connection con = DriverManager.getConnection(url,username,password);
				java.sql.Statement st = con.createStatement();
				int rowsAffected = st.executeUpdate(sql);;
				
				
				if (con != null) {
					System.out.println("Inserted Successfully");
					System.out.println(" rowsAffected "+rowsAffected);
					 
				} 
				else { System.out.println("Connection has been lost"); 
				         
				}
				
				  con.close();				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	
	
	
	
	
}

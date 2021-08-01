package com.qa.openkart.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	
	public static final String Login_Page_Title= "Account Login";
	public static final String cuponcode= "12356";
	public static final String country= "Greece";
	public static final String zone= "Epirus";


	
	public static List<String> getAccountSectionLIst()
	
	{
		
		      List<String> List = new ArrayList<String>();
		      
		      List.add("My Account");
		      List.add("My Orders");
		      List.add("My Affiliate Account");
		      List.add("Newsletter");
		      
		      return List; 
	}
	
	public static List<String> getCartAddedList()
	
	{
		
		List<String> list= new ArrayList<>();
		
		list.add("MacBook");
		list.add("MacBook Pro"); 
		return list; 
	}

	
	
	

}

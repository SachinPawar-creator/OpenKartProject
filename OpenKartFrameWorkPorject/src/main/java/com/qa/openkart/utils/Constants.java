package com.qa.openkart.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	
	public static final String Login_Page_Title= "Account Login";
	
	public static List<String> getAccountSectionLIst()
	
	{
		
		      List<String> List = new ArrayList<String>();
		      
		      List.add("My Account");
		      List.add("My Orders");
		      List.add("My Affiliate Account");
		      List.add("Newsletter");
		      
		      return List; 
	}
	

}

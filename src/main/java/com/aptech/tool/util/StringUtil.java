package com.aptech.tool.util;

public class StringUtil {
	public static Double strToDouble(Object object){
		if(object==null){
			return 0.00;
		}
		try{
			return Double.valueOf(String.valueOf(object));
		}catch(Exception e){
			e.printStackTrace();
			return 0.00;
		}
		
	}
}

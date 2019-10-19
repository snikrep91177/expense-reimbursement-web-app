package util;

import java.sql.Timestamp;
import java.util.Date;

public class Lumberjack {
	    private static Lumberjack obj; 
	  
	    // private constructor to force use of 
	    // getInstance() to create Singleton object 
	    private Lumberjack() {
	    	
	    } 
	  
	    public static Lumberjack getInstance() 
	    { 
	        if (obj==null) 
	            obj = new Lumberjack(); 
	        return obj; 
	    }
	    
	    public static void info(String info) {
	    	Date d = new Date();
	    	Timestamp ts = new Timestamp(d.getTime());
	    	
	    	System.out.println(ts + " " + info);
	    }
	} 

package com.epam.jmp.generator;

public class AgeStatistics {
	
	public static float getProcent(AgeMode mode){
	    switch(mode){
	        case SENIOR:
	            return 90;
	        case ADULT:
	        	return 45;
	        case YOUTH:
	        	return 25;
	        case CHILD:
	        	return 14;
	        default: 
	        	return 0;    
	    }
	}    
	
	public static int getAge(AgeMode mode){
	    switch(mode){
	        case SENIOR:
	            return 90;
	        case ADULT:
	        	return 45;
	        case YOUTH:
	        	return 25;
	        case CHILD:
	        	return 14;
	        default: 
	        	return 25;    
	    }
	}    

}

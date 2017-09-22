package com.javahash.spring.util;

public class RestPreconditions {
	
	 public static <T> T checkFound(final T resource) {
		 if (resource == null) {
		 throw new RuntimeException("Resource not found");
		 }
		 return resource;
	 }
	 
	 public static void checkNotNull(Object resource) {
		 if (resource == null) {
		 throw new RuntimeException("Resource not found");
		 }
	 }
	 
	 public static void checkValidInteger(String value) {
		 try {
			 Integer.parseInt(value);
		 }	 
		 catch(NumberFormatException e){
			 throw new RuntimeException("Bad Request");
		 }
	 }
}

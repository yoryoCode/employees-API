package com.javahash.spring.aop;

import java.util.List;

import javax.ws.rs.core.Response;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect  
public class ControllerErrorHandlerAspect {
	
	@Around("execution(* com.javahash.spring.controller.rest.*.findAll(..))")
    public Object getAllInternalErrorAdvice(ProceedingJoinPoint pjp) throws Throwable   
    {  
        System.out.println("Additional Concern Before calling actual method");  
        Object obj = null;
        try{
        	obj=pjp.proceed(); 
        
           	System.out.println("Additional Concern After calling actual method");  
            
            Response response = (Response) obj;
    		List elements = (List)response.getEntity();
    		
    		if(elements == null || elements.size() == 0){
    			obj = Response.status(204).entity(elements).build();
    			System.out.println("**** empty list **");
    		}                
        
        }
        catch(Exception e){
        	
        	if(e instanceof RuntimeException &&
        			e.getMessage().equals("Bad Request")){
        		
        		System.out.println("***bad request");
            	obj = Response.status(400).entity(null).build();
        		
        	} else {
        		
        		System.out.println("***generic error");
            	obj = Response.status(500).entity(null).build();
        		
        	}        	
        	
        }        
        
        return obj;  
    }
	
}

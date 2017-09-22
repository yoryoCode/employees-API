package com.javahash.spring.controller.rest;

import javax.ws.rs.core.Response;

public interface CrudController<T> {
	
	public Response findOne( String id );
	
	public Response create( T resource );
	
	public Response update( String id, T resource );
	
	public Response delete( String id );
	
}

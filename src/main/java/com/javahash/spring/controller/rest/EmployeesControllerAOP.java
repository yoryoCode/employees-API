package com.javahash.spring.controller.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.javahash.spring.model.Employee;
import com.javahash.spring.service.IEmployeesService;
import com.javahash.spring.util.RestPreconditions;
import com.javahash.spring.util.SearchCriteria;

@Controller
@Path("/employees")
public class EmployeesControllerAOP implements CrudController<Employee> {
	
	@Autowired
	public IEmployeesService employeeService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create( Employee resource ){
		System.out.println(resource + " init");
		RestPreconditions.checkNotNull( resource );
		employeeService.create(resource);
		System.out.println(" resource created");
		return Response.status(200).entity(resource).build();		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll(@QueryParam("age") final String age, @QueryParam("name") final String name){
		int statusCode = 200;
		Response response = null;
		Iterable<Employee> findAllResult = null;
		
		List<SearchCriteria> searchCriteriaList = new ArrayList<SearchCriteria>();
		
		if(age != null){
			RestPreconditions.checkValidInteger(age);
			SearchCriteria ageCriteria = new SearchCriteria("age", ":", age);
			searchCriteriaList.add(ageCriteria);
		}
		if(name != null){
			SearchCriteria nameCriteria = new SearchCriteria("name", ":", name);
			searchCriteriaList.add(nameCriteria);
		}		
		
		findAllResult = employeeService.findAll(searchCriteriaList);					
		
						
		response = Response.status(statusCode).entity(findAllResult).build();
		return response;
	}
	
	@GET
	@Path("/{employeeId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findOne(@PathParam("employeeId") final String employeeId) {
		RestPreconditions.checkValidInteger(employeeId);
		Employee employee = (Employee) employeeService.findById(Long.parseLong(employeeId));
		return Response.status(200).entity(employee).build();
	}
	
	@DELETE
	@Path("/{employeeId}")
	public Response delete(@PathParam("employeeId") String employeeId) {
		employeeService.delete(employeeId);
		String output = "DELETE: " + employeeId;
		return Response.status(204).entity(output).build();
	}

	@Override
	public Response update(String id, Employee resource) {
		// TODO Auto-generated method stub
		return null;
	}
	
}


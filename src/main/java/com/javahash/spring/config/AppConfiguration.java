package com.javahash.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.javahash.spring.service.IEmployeesService;
import com.javahash.spring.service.impl.EmployeesServiceImpl;

@Configuration
@ComponentScan(basePackages={"com.javahash.spring"})
@EnableWebMvc
@Import({MongoDBConfig.class, DaoConfig.class})
public class AppConfiguration {
	
	@Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
	
	@Bean(name="employeesService")
	public IEmployeesService getEmployeesService() {
	    return new EmployeesServiceImpl(); 
	}
	
}

package com.javahash.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.javahash.spring.aop.ControllerErrorHandlerAspect;

@Configuration
@ComponentScan(basePackages={"com.javahash.spring"})
@EnableAspectJAutoProxy(proxyTargetClass=true)
@EnableWebMvc
@Import({DaoConfig.class, MongoDBConfig.class, AppConfiguration.class})
public class AppConfigurationAOP {
	
	@Bean
    public ControllerErrorHandlerAspect myAspect() {
        return new ControllerErrorHandlerAspect();
    }
	
}


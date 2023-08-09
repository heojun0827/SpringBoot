package com.tjoeun.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Value("${uploadPath}")
  private String uploadPath;
	
  /*
    addResourceHandlers()  <-- resource 를 handling 하는 메소드 
  
    addResourceHandler("/images/**") : project 내부 경로 - web 에서 접근 경로 - logical path
    addResourceLocations(uploadPath) : 실제 PC(Server) 에서의 경로 - physical path
  */	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/images/**")
		        .addResourceLocations(uploadPath);
		        
	}
	
	
}





package com.fisnig.springBootCrud;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class StaffMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String dirNamenew = "staff-photos";
        
        

        Path staffPhotosDir = Paths.get(dirNamenew);
        
        


        String staffPhotosPath = staffPhotosDir.toFile().getAbsolutePath();
       
        
        registry.addResourceHandler("/"+dirNamenew+"/**")
                .addResourceLocations("file:/"+staffPhotosPath+"/");
    }

}

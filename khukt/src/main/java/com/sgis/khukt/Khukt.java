/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.sgis.khukt;

import com.sgis.khukt.model.Report;
import com.sgis.khukt.servlet.MyBean;
import org.eclipse.birt.report.engine.api.EngineException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 *
 * @author Nguyen Quoc Khanh
 */

@SpringBootApplication
public class Khukt {//extends SpringBootServletInitializer {

/*    @Autowired
    private Environment env;*/



    public static void main(String[] args) throws EngineException {
        SpringApplication.run(Khukt.class, args);


    }
/*
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                String urls = env.getProperty("cors.urls");
                CorsRegistration reg = registry.addMapping("/**");
                for (String url : urls.split(",")) {
                    reg.allowedOrigins(url);
                }
            }
        };
    }*/
}

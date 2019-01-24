package com.accenture.smsf.masterdata.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author haibo.liu
 * 
 */
@SpringCloudApplication
@Slf4j
@EnableFeignClients(basePackages = {"com.accenture.smsf"})
public class MasterdataApplication {
	
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(MasterdataApplication.class, args);
        log.info(" ========== " + applicationContext.getId() + " started ==========");
    }

    @Bean
    @Profile("dev")
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping( "/**")
                        .allowedOrigins("*")
                        .allowedMethods("*");
            }
        };
    }
}

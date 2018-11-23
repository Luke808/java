package com.accenture.masterdata.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

import lombok.extern.slf4j.Slf4j;

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
    
}

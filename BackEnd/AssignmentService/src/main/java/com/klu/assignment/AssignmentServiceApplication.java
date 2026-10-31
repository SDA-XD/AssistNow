
package com.klu.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages={"com.klu.assignment","com.klu.controller","com.klu.model","com.klu.repo","com.klu.service"})
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages="com.klu.repo")
@EntityScan(basePackages="com.klu.model")
public class AssignmentServiceApplication{

    public static void main(String[] args){
        SpringApplication.run(AssignmentServiceApplication.class,args);
        System.out.println("Assignment Service Started :)");
    }
}

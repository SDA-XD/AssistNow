package com.klu.complaint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"com.klu.complaint","com.klu.controller","com.klu.model","com.klu.repo","com.klu.service"})
@EntityScan("com.klu.model")
@EnableJpaRepositories("com.klu.repo")
public class ComplaintServiceApplication{
    public static void main(String[] args){
        SpringApplication.run(ComplaintServiceApplication.class,args);
    }
}
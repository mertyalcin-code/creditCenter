package com.mertyalcin.creditcenter;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CreditCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditCenterApplication.class, args);
    }
    // To create an instance of Model Mapper
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
    /*
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();


    }  */
}

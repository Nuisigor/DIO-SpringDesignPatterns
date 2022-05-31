package com.example.diodesignpatternsspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DioDesignPatternsSpringApplication {

  public static void main(String[] args) {
    SpringApplication.run(DioDesignPatternsSpringApplication.class, args);
  }

}

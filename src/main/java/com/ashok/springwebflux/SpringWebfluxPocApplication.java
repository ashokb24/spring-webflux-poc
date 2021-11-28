package com.ashok.springwebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ashok.springwebflux"})
public class SpringWebfluxPocApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringWebfluxPocApplication.class, args);
  }
}

package com.ashok.springwebflux.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;


import static org.springframework.http.MediaType.APPLICATION_JSON;
import static com.ashok.springwebflux.constants.ServiceConstants.GET_EMPLOYEES_PATH;
import static com.ashok.springwebflux.constants.ServiceConstants.GET_EMPLOYEE_PATH;
import static com.ashok.springwebflux.constants.ServiceConstants.DELETE_EMPLOYEE_PATH;
import static com.ashok.springwebflux.constants.ServiceConstants.CREATE_OR_UPDATE_EMPLOYEE_PATH;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.ashok.springwebflux.handler.EmployeeHandler;

@Configuration
@EnableConfigurationProperties(WebfluxService.class)
public class RouterConfiguration {

  @Autowired
  private EmployeeHandler employeeHandler;

  @Bean
  public RouterFunction<ServerResponse> getEmployees() {
    return route(GET(GET_EMPLOYEES_PATH).and(accept(APPLICATION_JSON)), employeeHandler::getAllEmployees);
  }

  @Bean
  public RouterFunction<ServerResponse> getEmployee() {
    return route(GET(GET_EMPLOYEE_PATH).and(accept(APPLICATION_JSON)), employeeHandler::getEmployee);
  }

  @Bean
  public RouterFunction<ServerResponse> deleteEmployee() {
    return route(DELETE(DELETE_EMPLOYEE_PATH).and(accept(APPLICATION_JSON)), employeeHandler::deleteEmployee);
  }

  @Bean
  public RouterFunction<ServerResponse> createEmployee() {
    return route(POST(CREATE_OR_UPDATE_EMPLOYEE_PATH).and(accept(APPLICATION_JSON)), employeeHandler::createEmployee);
  }

  @Bean
  public RouterFunction<ServerResponse> updateEmployee() {
    return route(PUT(CREATE_OR_UPDATE_EMPLOYEE_PATH).and(accept(APPLICATION_JSON)), employeeHandler::updateEmployee);
  }
}

package com.ashok.springwebflux.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ashok.springwebflux.constants.ServiceConstants;
import com.ashok.springwebflux.model.dto.ApiStatusDTO;
import com.ashok.springwebflux.model.dto.EmployeeDTO;
import com.ashok.springwebflux.services.EmployeeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeHandler {

  @Autowired
  EmployeeService employeeService;

  /**
   * Method to retrieve all the employee data
   * 
   * @param request
   * @return
   */
  public Mono<ServerResponse> getAllEmployees(ServerRequest request) {
    Flux<EmployeeDTO> employeeFlux = employeeService.getAllEmployees();
    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(employeeFlux, EmployeeDTO.class)
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  /**
   * Method to retrieve a Specific Employee
   * 
   * @param request
   * @return
   */
  public Mono<ServerResponse> getEmployee(ServerRequest request) {
    int employeeId = Integer.valueOf(request.pathVariable(ServiceConstants.Params.EMPLOYEE_ID));
    System.out.println("Retrieving the Employee Details for the EmployeeId " + employeeId);
    Mono<EmployeeDTO> employeeMono = employeeService.getEmployee(employeeId);
    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(employeeMono, EmployeeDTO.class)
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  /**
   * Method to delete a given Employee Data
   * 
   * @param request
   * @return
   */
  public Mono<ServerResponse> deleteEmployee(ServerRequest request) {
    int employeeId = Integer.valueOf(request.pathVariable(ServiceConstants.Params.EMPLOYEE_ID));
    Mono<ApiStatusDTO> apiStatusMono = employeeService.deleteEmployee(employeeId);
    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(apiStatusMono, ApiStatusDTO.class)
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  /**
   * Method to create a new Employee
   * 
   * @param request
   * @return
   */
  public Mono<ServerResponse> createEmployee(ServerRequest request) {
    Mono<EmployeeDTO> employeeDTOMono = request.bodyToMono(EmployeeDTO.class);
    return employeeDTOMono.flatMap(employeeDTO -> {
      Mono<ApiStatusDTO> apiStatusMono = employeeService.createEmployee(employeeDTO);
      return ServerResponse.created(request.uri()).contentType(MediaType.APPLICATION_JSON)
          .body(apiStatusMono, ApiStatusDTO.class).switchIfEmpty(ServerResponse.notFound().build());
    });
  }

  /**
   * Method to update an Existing Employee Details
   * 
   * @param request
   * @return
   */
  public Mono<ServerResponse> updateEmployee(ServerRequest request) {
    Mono<EmployeeDTO> employeeDTOMono = request.bodyToMono(EmployeeDTO.class);
    return employeeDTOMono.flatMap(employeeDTO -> {
      Mono<ApiStatusDTO> apiStatusMono = employeeService.updateEmployee(employeeDTO);
      return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(apiStatusMono, ApiStatusDTO.class)
          .switchIfEmpty(ServerResponse.notFound().build());
    });
  }
}

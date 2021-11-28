package com.ashok.springwebflux.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashok.springwebflux.constants.ServiceConstants;
import com.ashok.springwebflux.exception.EmployeeNotFoundException;
import com.ashok.springwebflux.model.Employee;
import com.ashok.springwebflux.model.dto.ApiStatusDTO;
import com.ashok.springwebflux.model.dto.EmployeeDTO;
import com.ashok.springwebflux.repository.EmployeeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {

  @Autowired
  EmployeeRepository employeeRepository;

  /**
   * Method to retrieve all the Employee Details
   * 
   * @return
   */
  public Flux<EmployeeDTO> getAllEmployees() {
    return employeeRepository.findAll().flatMap(employee -> {
      return Flux.just(new EmployeeDTO(employee));
    });
  }

  /**
   * Method to retrieve Employee Details for a given EmployeeId
   * 
   * @param employeeId
   * @return
   */
  public Mono<EmployeeDTO> getEmployee(Integer employeeId) {
    Mono<Boolean> employeeMono = employeeRepository.existsById(employeeId);
    return employeeMono.flatMap(exists -> {
      if (exists) {
        return employeeRepository.findById(employeeId).flatMap(employee -> {
          return Mono.just(new EmployeeDTO(employee));
        });
      } else {
        return Mono.error(
            new EmployeeNotFoundException(String.format(ServiceConstants.Messages.EMPLOYEE_NOT_FOUND, employeeId)));
      }

    });

  }

  /**
   * Method to delete the Employee details for a given EmployeeId
   * 
   * @param employeeId
   * @return
   */
  public Mono<ApiStatusDTO> deleteEmployee(Integer employeeId) {
    Mono<Boolean> employeeMono = employeeRepository.existsById(employeeId);
    return employeeMono.flatMap(exists -> {
      if (exists) {
        return employeeRepository.deleteById(employeeId).then(getDeleteResponse());
      } else {
        return Mono.error(
            new EmployeeNotFoundException(String.format(ServiceConstants.Messages.EMPLOYEE_NOT_FOUND, employeeId)));
      }
    });
  }

  /**
   * Method to return the DeleteResponse
   * 
   * @return
   */
  public Mono<ApiStatusDTO> getDeleteResponse() {
    return Mono.just(new ApiStatusDTO(ServiceConstants.Messages.EMPLOYEE_DELETED_SUCCESSFULLY));
  }

  /**
   * Method to create a new Employee Detail
   * 
   * @param employeeDTO
   * @return
   */
  public Mono<ApiStatusDTO> createEmployee(EmployeeDTO employeeDTO) {
    return employeeRepository.count().flatMap(employeeEntityCount -> {
      int employeeId = employeeEntityCount.intValue() + 1;
      Employee employeeObj = Employee.builder().employeeId(employeeId).firstName(employeeDTO.getFirstName())
          .lastName(employeeDTO.getLastName()).age(employeeDTO.getAge()).departmentName(employeeDTO.getDepartmentName())
          .build();
      return employeeRepository.save(employeeObj).flatMap(employee -> {
        return Mono.just(new ApiStatusDTO(ServiceConstants.Messages.EMPLOYEE_CREATED_SUCCESSFULLY));
      });
    });

  }

  /**
   * Method to update a Employee Detail
   * 
   * @param employeeDTO
   * @return
   */
  public Mono<ApiStatusDTO> updateEmployee(EmployeeDTO employeeDTO) {
    Mono<Boolean> employeeMono = employeeRepository.existsById(employeeDTO.getEmployeeId());
    return employeeMono.flatMap(exists -> {
      if (exists) {
        Employee employeeObj = Employee.builder().employeeId(employeeDTO.getEmployeeId())
            .firstName(employeeDTO.getFirstName()).lastName(employeeDTO.getLastName()).age(employeeDTO.getAge())
            .departmentName(employeeDTO.getDepartmentName()).build();
        return employeeRepository.save(employeeObj).flatMap(employee -> {
          return Mono.just(new ApiStatusDTO(ServiceConstants.Messages.EMPLOYEE_UPDATED_SUCCESSFULLY));
        });
      } else {
        return Mono.error(new EmployeeNotFoundException(
            String.format(ServiceConstants.Messages.EMPLOYEE_NOT_FOUND, employeeDTO.getEmployeeId())));
      }
    });

  }
}

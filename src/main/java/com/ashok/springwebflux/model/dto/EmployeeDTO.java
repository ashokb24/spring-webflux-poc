package com.ashok.springwebflux.model.dto;


import com.ashok.springwebflux.model.Employee;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 
 * Class to hold Employee Object
 * 
 * @author Ashok
 *
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class EmployeeDTO {
  @JsonInclude(Include.NON_NULL)
  private int employeeId;
  @JsonInclude(Include.NON_NULL)
  private String firstName;
  @JsonInclude(Include.NON_NULL)
  private String lastName;
  @JsonInclude(Include.NON_NULL)
  private String departmentName;
  @JsonInclude(Include.NON_NULL)
  private int age;

  public EmployeeDTO(Employee employee) {
    this.employeeId = employee.getEmployeeId();
    this.firstName = employee.getFirstName();
    this.lastName = employee.getLastName();
    this.departmentName = employee.getDepartmentName();
    this.age = employee.getAge();
  }

}

package com.ashok.springwebflux.model;

import java.util.Date;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Table
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Employee {
  @PrimaryKey
  @Column("EmployeeId")
  private int employeeId;
  @Column("first_name")
  private String firstName;
  @Column("last_name")
  private String lastName;
  @Column("department_name")
  private String departmentName;
  @Column("age")
  private int age;
}

package com.ashok.springwebflux.repository;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;

import com.ashok.springwebflux.model.Employee;


@EnableReactiveCassandraRepositories
public interface EmployeeRepository extends ReactiveCassandraRepository<Employee, Integer> {

}

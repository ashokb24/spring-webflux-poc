package com.ashok.springwebflux.constants;

/**
 * Class to define the constants
 * 
 * @author Ashok
 *
 */
public final class ServiceConstants {

  public static final String API_ROOT_URI = "/api/v1/webfluxservice";
  public static final String EMPLOYEE_PATH_PARAM_URI = "/{employeeId}";
  public static final String GET_EMPLOYEES_PATH = API_ROOT_URI + "/employees";
  public static final String CREATE_OR_UPDATE_EMPLOYEE_PATH = API_ROOT_URI + "/employee";
  public static final String GET_EMPLOYEE_PATH = API_ROOT_URI + EMPLOYEE_PATH_PARAM_URI + "/employeeId";
  public static final String DELETE_EMPLOYEE_PATH = API_ROOT_URI + EMPLOYEE_PATH_PARAM_URI + "/employeeId";
  public static final String UPDATE_EMPLOYEE_PATH = API_ROOT_URI + EMPLOYEE_PATH_PARAM_URI + "/employeeId";

  public final class Params {
    private Params() {}

    public static final String EMPLOYEE_ID = "employeeId";
  }

  public final class Messages {
    private Messages() {}

    public static final String EMPLOYEE_NOT_FOUND = "EmployeeId %d Not Found";
    public static final String EMPLOYEE_CREATED_SUCCESSFULLY = "Employee Created Successfully";
    public static final String EMPLOYEE_UPDATED_SUCCESSFULLY = "Employee Details Updated Successfully";
    public static final String EMPLOYEE_DELETED_SUCCESSFULLY = "Employee Deleted Successfully";

  }
}

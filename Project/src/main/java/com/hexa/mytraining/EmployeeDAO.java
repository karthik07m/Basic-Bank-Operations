package com.hexa.mytraining;

import com.hexa.mytraining.Employee;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

/**
 * The DAO class for employee.
 */
public interface EmployeeDAO  {
  /**
   * return all the details of all the employees.
   * @return the employee array
   */
  @SqlQuery("SELECT * FROM EMPOLYEE")
  @Mapper(EmployeeMapper.class)

  List<Employee> list();
  void close();

  @SqlUpdate("INSERT INTO EMPOLYEE VALUES ( :empId , :eName)")
  void insert(@Bind("empId") int empId, @Bind("eName") String eName);

  @SqlUpdate("UPDATE EMPOLYEE SET EMNAME = :eName WHERE EMPID = :empId")
  void update(@Bind("empId") int empId, @Bind("eName") String eName);
}

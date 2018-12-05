package com.hexa.mytraining;

import java.util.List;
import java.util.Objects;

import com.hexa.mytraining.DbConnection.*;


public class Employee {

    private int empId;
    private String empName;

    public Employee(int argEmpId, String empName) {
        this.empId = argEmpId;
        this.empName = empName;
    }
    public Employee() {
       
    }

    public final int getEmpId() {
        return empId;
    }

    public final String getName() {
        return empName;
    }

    public final void setEmpId(final int argEmpId) {
        this.empId = argEmpId;
    }

    public String message(String str)
    {
        return str;
    }

    public final void setEmpName(final String argEmpName) {
        this.empName = argEmpName;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Employee emp = (Employee) obj;
        if (Objects.equals(empId, emp.empId) && Objects.equals(empName, emp.empName)) {
            return true;
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(empId, empName);
    }

    @Override
    public String toString() {
        return "Emp Id:" + empId + "Emp Name" + empName;
    }

    
  private static EmployeeDAO dao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(EmployeeDAO.class);

  }

  
  public static Employee[] listAll() {

    List<Employee> es = dao().list();
    return es.toArray(new Employee[es.size()]);
  }

  public static String insertData(int empID, String eName)
  {
      dao().insert(empID, eName);
      return "Inserted";
  }

  public static String updateData(int empID, String eName)
  {
      dao().update(empID, eName);
      return "Updated";
  }


}
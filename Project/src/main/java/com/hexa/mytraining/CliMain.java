package com.hexa.mytraining;

import java.util.Scanner;

import com.hexa.mytraining.Employee;

public class CliMain
{

    private void listEmployeesDetails() {
        Employee[] employee = Employee.listAll();
        for (Employee e : employee) {
          System.out.println(e.getEmpId());
          System.out.println(e.getName());
        }
    }
    private void insertion(int empId, String eName)
    {
        String s = Employee.insertData(empId, eName);
        System.out.println(s);
        
    }
    private void upData(int empId, String eName)
    {
        String s = Employee.updateData(empId, eName);
        System.out.println(s);
        
    }

    public static void main(final String[] ar) {
        final CliMain mainObj = new CliMain();
        mainObj.listEmployeesDetails();
        mainObj.insertion(4444, "Mani");
        mainObj.upData(43928, "karthik4");
      }
}
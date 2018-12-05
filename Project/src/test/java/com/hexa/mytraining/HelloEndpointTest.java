package com.hexa.mytraining;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import org.junit.After;
import org.junit.AfterClass;



import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.*;
import org.junit.runner.RunWith;

import mockit.*;
import mockit.Expectations;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Mock;
import mockit.integration.junit4.JMockit;

public class HelloEndpointTest {
    //@Value("${local.server.port}")
    //int port;
    //Employee emp;  
   // @BeforeClass 
   // public static void beforeClass()
   /* {
        System.out.println("I am before class");
    }

    
    @Before
    public  void testBeforeMethod()
    {
        emp = new Employee(3001,"karthik");
        System.out.println("Object Created");
    }

    @Test
    public void testHello()  {
        String kar = emp.message("Hello World");
        assertEquals("Hello World", kar);
    }

    @Test 
    public void testGetSet()
    {
        int id = emp.getEmpId();
        String name = emp.getName();
        assertEquals(3001, id);
        assertEquals("Karthik", name);
    }

    @After
    public  void testAfterMethod()
    {
        emp = null;
        System.out.println("Cleaning Object");
    }

    @AfterClass
    public static void afterClass()
    {
        System.out.println("After the class");
    } */

    @Test
    public void testlistAll(@Mocked final EmployeeDAO dao)
    {
        new Expectations()
        {
            {
                ArrayList<Employee> list = new ArrayList<Employee>();
                list.add(new Employee(4391,"karthik"));
                list.add(new Employee(43928, "Gaurav"));
                dao.list();
                result = list;
            }
        };
        new MockUp<Employee>() {
            @Mock
            EmployeeDAO dao(){
                return dao;
            }
        };
        Employee[] employee = Employee.listAll();
        Employee emp[] = new Employee[2];
        emp[0] = new Employee(4391,"karthik");
        emp[1] = new Employee(43928, "Gaurav");
        assertArrayEquals(emp, employee);
    }

    @Test
    public void testInsert(@Mocked final EmployeeDAO dao)
    {
        new Expectations() {
        {
            dao.insert(43928, "karthik");
        }
        };
        new MockUp<Employee>() {
        @Mock
        EmployeeDAO dao()
        {
            return dao;
        }
        };

        String act = Employee.insertData(43928, "karthik");
        String excep = "Inserted";
        assertEquals(excep,act);
    }

    @Test
    public void testUpdate(@Mocked final EmployeeDAO dao)
    {
        new Expectations() {
        {
            dao.update(43928, "karthik");
        }
        };
        new MockUp<Employee>() {
        @Mock
        EmployeeDAO dao()
        {
            return dao;
        }
        };

        String act = Employee.updateData(43928, "karthik");
        String excep = "Updated";
        assertEquals(excep,act);
    }
}
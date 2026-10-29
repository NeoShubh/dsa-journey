package com.example.dsa.companies.accenture.stream;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class GroupEmployees {
    public static void main(String [] args){
        Employee e1 = new Employee("Shubham1","CS",1000);
        Employee e2 = new Employee("Shubham2","EC",1001);
        Employee e3 = new Employee("Shubham3","EC",1002);
        Employee e4 = new Employee("Shubham4","CS",1003);
        Employee e5 = new Employee("Shubham5","EC",1004);
        Employee e6 = new Employee("Shubham6","CS",1005);

        ArrayList <Employee> list = new ArrayList<>();
        list.add(e1);list.add(e2);list.add(e3);list.add(e4);list.add(e5);list.add(e6);
//        Map<String,Employee>mp =  list.stream().collect(Collectors.groupingBy(
//                Employee::getDept,
//                Collectors.maxBy(
//                        Comparator.comparing(Employee::getSalary)
//                )
//        ));
  Map<String,Optional<Employee>>mp = list.stream().collect(Collectors.groupingBy(Employee::getDept,Collectors.maxBy(Comparator.comparing(Employee::getSalary))));

    }
}

class Employee{
    String name;
    String dept;
    int salary;
    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }


    public Employee(String name, String dept, int salary) {
        this.name = name;
        this.dept = dept;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                ", salary=" + salary +
                '}';
    }

    public void setDept(String dept) {
        this.dept = dept;
    }


}
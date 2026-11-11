package com.example.dsa.companies.accenture.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SecondHighestSalary {
    public static void main(String [] args){
        Employee e1 = new Employee("Shubham1","CS5",1000);
        Employee e2 = new Employee("Shubham2","CS4",2000);
        Employee e3 = new Employee("Shubham3","CS3",3000);
        Employee e4 = new Employee("Shubham4","CS1",4000);
        Employee e5 = new Employee("Shubham5","CS2",5000);
        Employee e6 = new Employee("Shubham6","ECE",6000);
        Employee e7 = new Employee("Shubham7","CSE",7000);

        List<Employee> list = new ArrayList<>();
        list.add(e1);list.add(e2);list.add(e3);list.add(e4);list.add(e5);list.add(e6);list.add(e7);

//        list.stream().collect(Collectors.maxBy(Function.identity(),Collectors.counting()));

    }
}

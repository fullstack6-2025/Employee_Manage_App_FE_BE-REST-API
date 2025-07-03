package com.mysite.rest.employee.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.mysite.rest.employee.dto.EmployeeDTO;
import com.mysite.rest.employee.entity.Employee;
import com.mysite.rest.employee.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    
    //사원 리스트 
    public List<Employee> getEmployeeList() {
    	return employeeRepository.findAll();
    }
    
    //사원 정보 
    public Employee createEmployee(EmployeeDTO employeeDTO) {
    	Employee employee = new Employee(employeeDTO); 
    	
    	return employeeRepository.save(employee); 
    }
    
}

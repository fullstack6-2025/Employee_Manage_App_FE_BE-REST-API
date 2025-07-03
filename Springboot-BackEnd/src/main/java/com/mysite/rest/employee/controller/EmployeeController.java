package com.mysite.rest.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysite.rest.employee.dto.EmployeeDTO;
import com.mysite.rest.employee.entity.Employee;
import com.mysite.rest.employee.repository.EmployeeRepository;
import com.mysite.rest.employee.service.EmployeeService;
import com.mysite.rest.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")		//CORS 셋팅 
@RestController			// REST API 통신 
@RequestMapping ("/api/employee")		//@RequestMapping 하위 요청에 상속 됨. 
@RequiredArgsConstructor
public class EmployeeController {

	// @Controller :  <== MPA ( Server Side Randerring) 
		//  @GetMapping, @PostMapping 요청만 받는다. 백엔드 로직처리, 뷰페이지 를 리턴 
	
	// @RestController : SPA ( Client Side Randerring ) 
		// JSON 의로 값을 클라이언트에 던져줌. Client 에서 JSON 으로 값을 받아서 DB에 저장함. 
		//  - @GetMapping(DB에서 Select) , 
		//  - @PostMapping(DB에 Insert )  
		//  - @PutMapping (DB의 값을 Update)
		//  - @DeleteMapping (DB의 값을 delete) 
	
	// CRUD REST 통신 처리 블락 
	// get : 사원의 전체 리스트 정보  : /api/employee
	// http://localhost:9999/api/employee
	
	// get : 사원의 정보 : /api/employee/{id}
	//http://localhost:9999/api/employee/1
	
	// post : 사원을 등록 : /api/employee 
	// http://localhost:9999/api/employee?firstName=감찬&lastName=강&emailId=aaa@aaa.com
	
	// put : 사원정보를 수정 : /api/employee/{id}
	//http://localhost:9999/api/employee/1?firstName=대왕&lastName=세종&emailId=bbb@bbb.com
	
	// delete : 사원정보를 삭제 : /api/employee/{id} 
	//http://localhost:9999/api/employee/2
	
	
	
    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService; 

    @GetMapping
    public List<Employee> getAllEmployees(){ 
    	System.out.println("getAllEmployees : 호출됨 ");
        return employeeService.getEmployeeList();
    }

    // build create employee REST API
    @PostMapping
   public Employee createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }

    // build get employee by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable  long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
        return ResponseEntity.ok(employee);
    }

    // build update employee REST API
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails) {
        Employee updateEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());
        updateEmployee.setEmailId(employeeDetails.getEmailId());

        employeeRepository.save(updateEmployee);

        return ResponseEntity.ok(updateEmployee);
    }

    // build delete employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        employeeRepository.delete(employee);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
	
}

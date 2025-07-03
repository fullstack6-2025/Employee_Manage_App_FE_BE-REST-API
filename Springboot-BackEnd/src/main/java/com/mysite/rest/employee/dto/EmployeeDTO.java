package com.mysite.rest.employee.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {
	
	private long id; 

	private String firstName; 
	
	private String lastName; 
	
	private String emailId; 
	
}

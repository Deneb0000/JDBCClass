package org.doit.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class EmpDeptSalGradelVO {
	
	private int empno;
	private String ename; 
	private double pay; 
	private LocalDateTime hiredate;
	private String dname;
	private int grade;


} //class

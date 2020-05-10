package com.bhk.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bhk.dto.Student;

@Service
public class StudentService {

	public List<Student> getAllStudent(){
		List<Student> lstStudent = new ArrayList<Student>();
		Student student1 = new Student();
		student1.setId(123123l);
		student1.setName("Bhaskar Koley");		
		lstStudent.add(student1);
		return lstStudent;
	}
}

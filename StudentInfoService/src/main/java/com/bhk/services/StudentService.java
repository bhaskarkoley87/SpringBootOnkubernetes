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
		student1.setCity("Kolkata");
		student1.setAddress("Sector 5");
		student1.setMobile("9943759347");
		student1.setCountry("India");
		lstStudent.add(student1);
		return lstStudent;
	}
}

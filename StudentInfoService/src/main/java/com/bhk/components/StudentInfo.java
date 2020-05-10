package com.bhk.components;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bhk.dto.Student;
import com.bhk.services.StudentService;

@RestController
@RequestMapping(value="/studentdetails")
public class StudentInfo {

	@Autowired
	StudentService studentService;
	
	private Logger log = LogManager.getLogger(StudentInfo.class.getName());
	
	@RequestMapping(value="/getallstudent", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) 
	public List<Student> getAllStudent() throws Exception{
		log.info("Request comes to StudentInfo service...");
		return studentService.getAllStudent();
	}
}

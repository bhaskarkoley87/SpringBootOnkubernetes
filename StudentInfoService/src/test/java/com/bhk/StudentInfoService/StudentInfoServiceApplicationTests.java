package com.bhk.StudentInfoService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bhk.controller.StudentInfo;
import com.bhk.dto.Student;
import com.bhk.services.StudentService;
import com.bhk.swagger.SwaggerConfig;
import com.bhk.swagger.SwaggerConfigProperties;

@SpringBootTest
@AutoConfigureMockMvc
class StudentInfoServiceApplicationTests {
	private MockMvc mockMvc;
	private MockMvc mockMvcSwagger;

	// private Logging log = Logging.

	@InjectMocks
	StudentInfo studentinfo;

	@InjectMocks
	StudentService studentService;
	
	@Autowired
	Student student1;
	
	@Autowired
	SwaggerConfigProperties swaggerConfigProperties;
	
	@InjectMocks
	SwaggerConfig swaggerConfig;

	@BeforeEach
	public void setUpForTest() throws Exception {

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(studentinfo).build();
		mockMvcSwagger = MockMvcBuilders.standaloneSetup(swaggerConfig).build();
	}

	@Test
	public void contextLoads() throws Exception {
		ReflectionTestUtils.setField(studentinfo, "studentService", studentService);
		assertEquals(true, studentinfo.getAllStudent().size() > 0);
		mockMvc.perform(MockMvcRequestBuilders.get("/studentdetails/getallstudent")
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").isNotEmpty());
		mockMvc.perform(MockMvcRequestBuilders.post("/studentdetails").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().is(404));
	}

	@Test
	public void contextLoadsswaggerTester() throws Exception {
		swaggerConfigProperties = new SwaggerConfigProperties();
		mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/swagger-ui.html")
				.contentType(MediaType.APPLICATION_XHTML_XML)).andExpect(MockMvcResultMatchers.status().is(404));
		
	}
}

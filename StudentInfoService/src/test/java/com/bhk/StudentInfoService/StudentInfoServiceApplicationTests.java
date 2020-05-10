package com.bhk.StudentInfoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bhk.components.StudentInfo;


@SpringBootTest
class StudentInfoServiceApplicationTests {
	private MockMvc mockMvc;
	
	//private Logging log = Logging.
	
	@InjectMocks
	StudentInfo studentinfo;
	
	@BeforeEach
	public void setUpForTest() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(studentinfo).build();
	}

	@Test
	public void contextLoads() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/studentdetails/getallstudent").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isOk());
	}

}

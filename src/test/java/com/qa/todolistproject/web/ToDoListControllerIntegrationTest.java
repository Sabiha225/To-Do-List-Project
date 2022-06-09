package com.qa.todolistproject.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.todolistproject.domain.ToDoList;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) 
@AutoConfigureMockMvc 
@Sql(scripts = { "classpath:todolist-schema.sql",
		"classpath:todolist-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class ToDoListControllerIntegrationTest {
	
	@Autowired 
	private MockMvc mvc; 

	@Autowired
	private ObjectMapper mapper; 
	
	@Test
	void testCreate() throws Exception {
		ToDoList testtask = new ToDoList(1, "Finish project");
		String testToDoListAsJSON = this.mapper.writeValueAsString(testtask);
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(testToDoListAsJSON);

		ToDoList testCreatedtask = new ToDoList(3, "Buy groceries");
		String testCreatedtaskAsJSON = this.mapper.writeValueAsString(testCreatedtask);
		ResultMatcher checkStatus = status().isCreated(); 
		ResultMatcher checkBody = content().json(testCreatedtaskAsJSON); 

		// this sends the request and then checks the status and the body
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}
	
	@Test
	void getAllTest() throws Exception {
		RequestBuilder req = get("/getAll");
		List<ToDoList> testtask = List.of(new ToDoList(1, "Finish project"), new ToDoList(2, "Clean room"), new ToDoList(3, "Buy groceries"), new ToDoList(4, "Email documents"));
        String json = this.mapper.writeValueAsString(testtask);
        ResultMatcher checkStatus = status().isOk();
        ResultMatcher checkBody = content().json(json);
        
        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	@Test
	void getTest() throws Exception {
		RequestBuilder req = get("/get/1");
		String taskAsJson = this.mapper.writeValueAsString(new ToDoList(1, "Finish project"));
		ResultMatcher checkStatus = status().isOk();
	    ResultMatcher checkBody = content().json(taskAsJson);
	    
	    this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	  	
	        
	}
	
	@Test
	void getBytaskTest() throws Exception {
		RequestBuilder req = get("/getBytask/Finish project");
		List<ToDoList> testtodolist = List.of(new ToDoList(1, "Finish project"), new ToDoList(4, "Email documents"));
		String json = this.mapper.writeValueAsString(testtodolist);
		ResultMatcher checkStatus = status().isOk();
	    ResultMatcher checkBody = content().json(json);
	    
	    this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}
	@Test
	void testRemove() throws Exception {
		this.mvc.perform(delete("/remove/Finish project")).andExpect(status().isNoContent());
	}


}
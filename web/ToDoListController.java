package com.qa.todolistproject.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.todolistproject.domain.ToDoList;
import com.qa.todolistproject.service.ToDoListService;

@CrossOrigin
@RestController //this tells Spring that this is a controller
public class ToDoListController {

	private ToDoListService service;
	
	@Autowired
	public ToDoListController(ToDoListService service) {
		super();
		this.service=service;
	}
	
	@PostMapping("/create") // @RequestBody pulls the parameter from the body of the request

	public ResponseEntity<ToDoList> createToDoList(@RequestBody ToDoList c) {
		ToDoList created = this.service.create(c);
		ResponseEntity<ToDoList> response = new ResponseEntity<ToDoList>(created, HttpStatus.CREATED); // 201 - created
		return response;
	}
	
	@GetMapping("/getAll") 
	public ResponseEntity<List<ToDoList>> getAllToDoList() {
		return ResponseEntity.ok(this.service.getAll()); //200 - OK
	}
	
	@GetMapping("/get/{id}")  // 200 OK
	public ToDoList getCinemaID(@PathVariable Integer id) {
		return this.service.getOne(id);
	}
	@PutMapping("/replace/{id}") // 202 accepted
	public ResponseEntity<ToDoList> replaceCinema(@PathVariable Integer id, @RequestBody ToDoList newCinema) {
		ToDoList body = this.service.replace(id, newCinema);
		ResponseEntity<ToDoList> response = new ResponseEntity<ToDoList>(body, HttpStatus.ACCEPTED);
		return response;
	}
	@DeleteMapping("/remove/{id}") // 204 no content 
	public ResponseEntity<?> removeCinemaID(@PathVariable Integer id) {
		this.service.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/getBytask/{task}") //200 OK
	public ResponseEntity<List<ToDoList>> gettaskBytask(@PathVariable String task) {
	List<ToDoList> found = this.service.getAllbytask(task);
	return ResponseEntity.ok(found);
	}
	
}
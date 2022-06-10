package com.qa.todolistproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

	import com.qa.todolistproject.domain.ToDoList;
	import com.qa.todolistproject.repo.ToDoListRepo;

	@Service
	public class ToDoListService implements ServiceIF<ToDoList>{
		
		private ToDoListRepo repo;

		@Autowired
		public ToDoListService(ToDoListRepo repo) {
			super();
			this.repo=repo;
		}
		
		// CREATE
			public ToDoList create(ToDoList c) {
				ToDoList created = this.repo.save(c);  
				return created;
			}

			// READ
			public List<ToDoList> getAll() {
				return this.repo.findAll();
			}

			public ToDoList getOne(Integer id) {
				Optional<ToDoList> found = this.repo.findById(id);
				return found.get(); 
				
			}
			
			
			

			// DELETE
			public void remove(@PathVariable Integer id) {
				this.repo.deleteById(id); //DELETE FROM ToDoList WHERE id= 
			}

			@Override
			public ToDoList replace(Integer id, ToDoList t) {
				// TODO Auto-generated method stub
				return null;
			}

		

	}


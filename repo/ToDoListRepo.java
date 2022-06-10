package com.qa.todolistproject.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.todolistproject.domain.ToDoList;

@Repository
public interface ToDoListRepo extends JpaRepository<ToDoList, Integer>{

	List<ToDoList> findByToDoListIgnoreCase(String task);
	

	
}
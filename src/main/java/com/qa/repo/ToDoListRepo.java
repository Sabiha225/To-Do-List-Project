package com.qa.todolistproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.todolistproject.domain.ToDoList;

@Repository
public interface ToDoListRepo extends JpaRepository<ToDoList, Integer>{


	

	
}

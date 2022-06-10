package com.qa.todolistproject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

	@Entity
	public class ToDoList {
		
		@Id //PRIMARY KEY
		@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENT
		private Integer id;
		
		@Column(nullable = false)
		private String task;



		
		
		
		public ToDoList(int i, String string) {
			this.id = i;
		    this.task = string;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String gettask() {
			return task;
		}

		public void settask(String task) {
			this.task = task;
		}

		@Override
		public String toString() {
			return "ToDoList [id=" + id + ", task=" + task + "]";
		}
		
}

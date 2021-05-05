package com.farkalit.webdemo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.farkalit.webdemo.model.Todo;

@Service
public class TodoService {

	private static List<Todo> todos = new ArrayList<>();

	static {
		todos.add(new Todo(1, "usmanweb", "Learn Spring MVC", new Date(), false));
		todos.add(new Todo(2, "usmanweb", "Learn Struts", new Date(), false));
		todos.add(new Todo(3, "usmanweb", "Learn Hibernate", new Date(), false));
	}

	public List<Todo> retrieveTodos(String user) {
		List<Todo> filteredTodos = new ArrayList<>();
		for (Todo todo : todos) {
			if (todo.getUser().equals(user)) {
				filteredTodos.add(todo);
			}
		}
		return filteredTodos;
	}
}

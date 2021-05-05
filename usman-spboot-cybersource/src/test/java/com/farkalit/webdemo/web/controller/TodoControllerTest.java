package com.farkalit.webdemo.web.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ModelMap;

import com.farkalit.webdemo.model.Todo;
import com.farkalit.webdemo.service.TodoService;

@RunWith(SpringRunner.class)
public class TodoControllerTest {

	@InjectMocks
	private TodoController todo;

	@Mock
	private ModelMap model;
	
	@Mock
	private TodoService service;
	
	@Test
	public void testTestController() {
		List<Todo> todoList = new ArrayList<>();
		when(service.retrieveTodos(Mockito.anyString())).thenReturn(todoList);
		String showTodos = todo.showTodos(model);
		assertNotNull(showTodos);
	}
}

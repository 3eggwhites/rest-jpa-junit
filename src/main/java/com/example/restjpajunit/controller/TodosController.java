package com.example.restjpajunit.controller;

import com.example.restjpajunit.model.TodosModel;
import com.example.restjpajunit.service.ITodosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodosController {

    @Autowired
    private ITodosService todosService;

    @GetMapping
    public List<TodosModel> getTodos() {
        return todosService.getAllTodos();
    }

    @GetMapping(value = "{id}")
    public TodosModel getTodosById(@PathVariable("id") Long id) {
        return todosService.getTodosById(id);
    }
}

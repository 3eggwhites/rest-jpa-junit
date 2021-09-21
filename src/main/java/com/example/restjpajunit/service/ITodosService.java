package com.example.restjpajunit.service;

import com.example.restjpajunit.data.entity.Todos;
import com.example.restjpajunit.model.TodosModel;

import java.util.List;

public interface ITodosService {

    List<TodosModel> getAllTodos();

    TodosModel getTodosById(Long id);

    void saveTodos(TodosModel todo);

    void removeTodos(TodosModel todo);
}

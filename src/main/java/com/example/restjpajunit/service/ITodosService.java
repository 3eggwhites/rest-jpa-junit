package com.example.restjpajunit.service;

import com.example.restjpajunit.data.entity.Todos;
import com.example.restjpajunit.exception.TodosException;
import com.example.restjpajunit.model.TodosModel;

import java.util.List;

public interface ITodosService {

    List<TodosModel> getAllTodos();

    TodosModel getTodosById(Long id) throws TodosException;

    TodosModel saveTodos(TodosModel todo) throws TodosException;

    void removeTodosById(Long id) throws TodosException;

    void removeTodos();
}

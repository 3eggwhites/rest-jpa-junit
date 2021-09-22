package com.example.restjpajunit.controller;

import com.example.restjpajunit.exception.TodosException;
import com.example.restjpajunit.model.TodosModel;
import com.example.restjpajunit.service.ITodosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodosController {

    @Autowired
    private ITodosService todosService;

    @GetMapping
    @Cacheable(value = "todos")
    public ResponseEntity<List<TodosModel>> getTodos() {
        return new ResponseEntity<List<TodosModel>>(todosService.getAllTodos(), HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    @Cacheable(value = "todos")
    public ResponseEntity<TodosModel> getTodosById(@PathVariable("id") Long id) throws TodosException {
        return new ResponseEntity<TodosModel>(todosService.getTodosById(id), HttpStatus.OK);
    }

    @DeleteMapping(value="{id}")
    @CacheEvict(cacheNames="todos", allEntries=true)
    public void deleteTodosById(@PathVariable("id") Long id) throws TodosException {
        todosService.removeTodosById(id);
    }

    @DeleteMapping(value="/deleteAll")
    @CacheEvict(cacheNames="todos", allEntries=true)
    public void deleteAllTodos() {
        todosService.removeTodos();
    }

    @PutMapping(value = "/updateTodos")
    @CachePut(cacheNames = "todos")
    public ResponseEntity<TodosModel> updateTodos(@RequestBody TodosModel updatedTodo) throws TodosException {
        return new ResponseEntity<TodosModel>(todosService.saveTodos(updatedTodo), HttpStatus.OK);
    }
}

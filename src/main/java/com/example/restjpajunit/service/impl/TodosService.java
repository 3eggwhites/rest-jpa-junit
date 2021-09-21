package com.example.restjpajunit.service.impl;

import com.example.restjpajunit.data.entity.Todos;
import com.example.restjpajunit.data.repository.ITodosRepository;
import com.example.restjpajunit.mappers.ITodosMapper;
import com.example.restjpajunit.model.TodosModel;
import com.example.restjpajunit.service.ITodosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodosService implements ITodosService {

    @Autowired
    private ITodosRepository repository;

    @Autowired
    private ITodosMapper mapper;

    @Override
    public List<TodosModel> getAllTodos() {
        List<Todos> todos = (List<Todos>) repository.findAll();
        List<TodosModel> todosModelList = new ArrayList<>();
        todosModelList = mapper.domainToModelList(todos);
        return todosModelList;
    }

    @Override
    public TodosModel getTodosById(Long id) {
        TodosModel model = null;
        Todos todos = repository.findById(id).orElse(null);
        model = mapper.domainToModel(todos);
        return model;
    }

    @Override
    public void saveTodos(TodosModel todoModel) {
        Todos newEntity = mapper.modelToDomain(todoModel);
        repository.save(newEntity);
    }

    @Override
    public void removeTodos(TodosModel todoModel) {
        Todos deleteEntity = mapper.modelToDomain(todoModel);
        repository.delete(deleteEntity);
    }
}

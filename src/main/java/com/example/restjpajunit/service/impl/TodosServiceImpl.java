package com.example.restjpajunit.service.impl;

import com.example.restjpajunit.data.entity.Todos;
import com.example.restjpajunit.data.repository.ITodosRepository;
import com.example.restjpajunit.exception.TodosException;
import com.example.restjpajunit.mappers.ITodosMapper;
import com.example.restjpajunit.model.TodosModel;
import com.example.restjpajunit.service.ITodosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodosServiceImpl implements ITodosService {

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
    public TodosModel getTodosById(Long id) throws TodosException {
        TodosModel model = null;
        Todos todos = repository.findById(id).orElse(null);
        if (null == todos) {
            throw new TodosException("Data not found");
        }
        model = mapper.domainToModel(todos);
        return model;
    }

    @Override
    public TodosModel saveTodos(TodosModel todoModel) throws TodosException {
        Todos entity = repository.findById(todoModel.getId()).orElse(null);
        if (null != entity) {
            entity.setCompleted(todoModel.getCompleted());
            entity.setText(todoModel.getText());
        } else {
            throw new TodosException("Data not found");
        }
        Todos updatedEntity = repository.save(entity);
        TodosModel updatedModel = mapper.domainToModel(updatedEntity);
        return updatedModel;
    }

    @Override
    public void removeTodosById(Long id) throws TodosException {
        Todos toBeDeleted = repository.findById(id).orElse(null);
        if (null != toBeDeleted) {
            repository.delete(toBeDeleted);
        } else {
            throw new TodosException("Data not found");
        }
    }

    @Override
    public void removeTodos() {
        repository.deleteAll();
    }
}

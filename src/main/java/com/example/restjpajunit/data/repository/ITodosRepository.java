package com.example.restjpajunit.data.repository;

import com.example.restjpajunit.data.entity.Todos;
import org.springframework.data.repository.CrudRepository;

public interface ITodosRepository extends CrudRepository<Todos, Long> {
}

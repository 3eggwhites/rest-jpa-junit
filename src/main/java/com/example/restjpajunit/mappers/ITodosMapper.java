package com.example.restjpajunit.mappers;

import com.example.restjpajunit.data.entity.Todos;
import com.example.restjpajunit.model.TodosModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ITodosMapper {
    TodosModel domainToModel(final Todos todos);

    List<TodosModel> domainToModelList(final List<Todos> todosList);

    Todos modelToDomain(final TodosModel model);
}

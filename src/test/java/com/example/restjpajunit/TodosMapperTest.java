package com.example.restjpajunit;

import com.example.restjpajunit.data.entity.Todos;
import com.example.restjpajunit.mappers.ITodosMapper;
import com.example.restjpajunit.model.TodosModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class TodosMapperTest {

	private final ITodosMapper mapper = Mappers.getMapper(ITodosMapper.class);

	@DisplayName("Testing mapping list of entities")
	@Test
	public void testTodosListMapping() {
		List<Todos> todosList = new ArrayList<>();
		List<TodosModel> modelList = null;
		Todos t1 = new Todos();
		Todos t2 = new Todos();
		todosList.add(t1);
		todosList.add(t2);
		modelList = mapper.domainToModelList(todosList);
		assertNotNull(modelList);
		assertEquals(2, modelList.size());
	}

	@DisplayName("Testing mapping between single entity and model")
	@Test
	public void testSingleEntityMapping() {
		Todos t1 = new Todos();
		t1.setCompleted(true);
		TodosModel tm = null;
		tm = mapper.domainToModel(t1);
		assertNotNull(tm);
		assertTrue(tm.getCompleted());
	}

	@DisplayName("Testing mapping between model and entity")
	@Test
	public void testModelToEntityMapping() {
		Todos t1 = null;
		TodosModel tm = new TodosModel();
		tm.setCompleted(false);
		t1 = mapper.modelToDomain(tm);
		assertNotNull(t1);
		assertFalse(t1.getCompleted());
	}
}

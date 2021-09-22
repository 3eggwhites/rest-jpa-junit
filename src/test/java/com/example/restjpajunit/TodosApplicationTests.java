package com.example.restjpajunit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = TodosApplication.class)
class TodosApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	@DisplayName("Verify get all Todos")
	public void verifyGetAllTodos() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/todos").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(4))).andDo(print());
	}

	@Test
	@DisplayName("Verify get single Todos")
	public void verifyGetSingleTodos() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/todos/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.completed").isBoolean())
				.andExpect(jsonPath("$.completed").value(true))
				.andDo(print());
	}

	@Test
	@DisplayName("Verify exception get single Todos")
	public void verifyExceptionSingleTodos() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/todos/10").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.errorCode").exists())
				.andExpect(jsonPath("$.errorCode").value(404))
				.andDo(print());
	}

	@Test
	@DisplayName("Verify update Todos")
	public void verifyUpdateTodos() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.put("/todos/updateTodos")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"id\" : \"4\", \"completed\" : \"true\" }")
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.id").value(4))
				.andExpect(jsonPath(("$.completed")).exists())
				.andExpect(jsonPath(("$.completed")).isBoolean())
				.andExpect(jsonPath(("$.completed")).value(true))
				.andDo(print());
	}

	@Test
	@DisplayName("Verify update failed on invaild Todos item")
	public void verifyUpdateFailedTodos() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.put("/todos/updateTodos")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"id\" : \"5\", \"completed\" : \"true\" }")
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.errorCode").exists())
				.andExpect(jsonPath("$.errorCode").value(404))
				.andExpect(jsonPath(("$.message")).exists())
				.andExpect(jsonPath(("$.message")).value("Data not found"))
				.andDo(print());
	}

}

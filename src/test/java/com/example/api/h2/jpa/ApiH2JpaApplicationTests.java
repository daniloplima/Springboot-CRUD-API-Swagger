package com.example.api.h2.jpa;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.example.api.h2.jpa.model.TodoTEST;

@SpringBootTest
@AutoConfigureMockMvc
class ApiH2JpaApplicationTests {

	@Autowired
	private MockMvc mvc;

	ObjectMapper ObjectMapper = new ObjectMapper();

	@Test
	public void GETAllTodos() throws Exception {
		mvc.perform(get("/api/v1/todo")).andExpect(status().isOk());

	}

	@Test
	public void POSTTodo() throws Exception {

		TodoTEST todotest = new TodoTEST("Study","Learn JAVA testing","NOT_COMPLETED");

		mvc.perform(post("/api/v1/todo")
				.contentType(MediaType.APPLICATION_JSON)
				.content(ObjectMapper.writeValueAsString(todotest))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful()
		);
	}

	@Test
	public void DELETETodo() throws Exception {
		mvc.perform(delete("/api/v1/todo/2")).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void PUTTodo() throws Exception {

		TodoTEST todotest = new TodoTEST("Go Shopping","Buy new furniture","NOT_COMPLETED");

		mvc.perform(put("/api/v1/todo/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(ObjectMapper.writeValueAsString(todotest))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful()
				);
	}



}

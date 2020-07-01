package com.merrylab.example.ndjson.config.converter

import com.merrylab.example.ndjson.TodoService
import com.merrylab.example.ndjson.controller.BulkController
import com.merrylab.example.ndjson.domain.ToDoRequest
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@SpringBootTest
internal class TodoListHttpMessageConverterTest {

    private lateinit var mockMvc: MockMvc
    private val todoService: TodoService = mockk()

    @BeforeEach
    fun setUp() {
        val multiEventController = BulkController(todoService)
        val builder = MockMvcBuilders
                .standaloneSetup(multiEventController)
                .setMessageConverters(TodoListHttpMessageConverter(),
                        MappingJackson2HttpMessageConverter())
        mockMvc = builder.build()
    }

    @Test
    fun convertNdJsonToRequestModel() {
        val requestBody = """
            { "id": "test_1", "name": "Work", "priority": 1 }
            { "id": "test_2", "name": "Todo", "priority": 2 }
        """.trimIndent()

        val expected = listOf(
                ToDoRequest(id = "test_1", name = "Work", priority = 1),
                ToDoRequest(id = "test_2", name = "Todo", priority = 2)
        )

        val builder = MockMvcRequestBuilders
                .post("/bulk")
                .contentType("application/x-ndjson")
                .content(requestBody)

        val slot = slot<List<ToDoRequest>>()
        every { todoService.saveTodos(capture(slot)) } just Runs

        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andDo(MockMvcResultHandlers.print())

        val todoRequests = slot.captured
        Assertions.assertThat(todoRequests).containsAll(expected)
    }
}
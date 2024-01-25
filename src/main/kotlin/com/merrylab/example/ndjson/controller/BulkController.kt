package com.merrylab.example.ndjson.controller

import com.merrylab.example.ndjson.TodoService
import com.merrylab.example.ndjson.domain.Todo
import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class BulkController(private val todoService: TodoService) {

    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    fun bulkInput(@RequestBody requestBody: List<Todo>) {
        todoService.saveTodos(requestBody)
    }

    @GetMapping("/todos", produces = [MediaType.APPLICATION_NDJSON_VALUE])
    fun getTodos(): Flow<Todo> {
        return todoService.getTodos()
    }
}

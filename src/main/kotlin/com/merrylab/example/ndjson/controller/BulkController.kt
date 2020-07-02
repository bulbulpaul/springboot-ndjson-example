package com.merrylab.example.ndjson.controller

import com.merrylab.example.ndjson.TodoService
import com.merrylab.example.ndjson.domain.Todo
import org.springframework.http.HttpStatus
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

    @GetMapping("/todos", produces = ["application/x-ndjson"])
    fun getTodos(): List<Todo> {
        return todoService.getTodos()
    }
}
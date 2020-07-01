package com.merrylab.example.ndjson.controller

import com.merrylab.example.ndjson.TodoService
import com.merrylab.example.ndjson.domain.Todo
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class BulkController(private val todoService: TodoService) {

    @PostMapping("/bulk", produces = ["application/x-ndjson"])
    @ResponseStatus(HttpStatus.CREATED)
    fun bulkInput(@RequestBody requestBody: List<Todo>): List<Todo> {
        todoService.saveTodos(requestBody)
        return requestBody
    }

}
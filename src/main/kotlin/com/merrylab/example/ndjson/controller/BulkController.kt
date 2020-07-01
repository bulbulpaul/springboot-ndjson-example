package com.merrylab.example.ndjson.controller

import com.merrylab.example.ndjson.ToDoService
import com.merrylab.example.ndjson.domain.ToDoListRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class BulkController(private val toDoService: ToDoService) {

    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    fun bulkInput(@RequestBody requestBody: ToDoListRequest): ToDoListRequest {
        toDoService.saveTodos(requestBody.todoList)
        return requestBody
    }

}
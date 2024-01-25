package com.merrylab.example.ndjson

import com.merrylab.example.ndjson.domain.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import org.springframework.stereotype.Service

@Service
class TodoService {

    fun saveTodos(todoList: List<Todo>) {
        // impl code...
    }

    fun getTodos(): Flow<Todo> {
        return listOf(
                Todo(id = "test_1", name = "Work", priority = 1),
                Todo(id = "test_2", name = "Todo", priority = 2)
        ).asFlow()
    }
}

package com.merrylab.example.ndjson.domain

data class ToDoRequest(val id: String, val name: String, val priority: Int)

data class ToDoListRequest(val todoList: List<ToDoRequest>)
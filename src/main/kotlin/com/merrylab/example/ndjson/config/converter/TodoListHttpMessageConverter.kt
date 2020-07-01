package com.merrylab.example.ndjson.config.converter

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.merrylab.example.ndjson.domain.ToDoListRequest
import com.merrylab.example.ndjson.domain.ToDoRequest
import org.springframework.http.HttpInputMessage
import org.springframework.http.HttpOutputMessage
import org.springframework.http.MediaType
import org.springframework.http.converter.AbstractHttpMessageConverter


class TodoListHttpMessageConverter :
        AbstractHttpMessageConverter<ToDoListRequest>(
                // set target content type
                MediaType("application", "x-ndjson")
        ) {

    private val objectMapper = jacksonObjectMapper()

    override fun supports(clazz: Class<*>): Boolean {
        return ToDoListRequest::class.java.isAssignableFrom(clazz)
    }

    override fun writeInternal(t: ToDoListRequest, outputMessage: HttpOutputMessage) {
        val responses = t.todoList.map { objectMapper.writeValueAsString(it) }.joinToString("\n")
        outputMessage.body.bufferedWriter().use { it.write(responses) }
    }

    override fun readInternal(clazz: Class<out ToDoListRequest>, inputMessage: HttpInputMessage): ToDoListRequest {
        val requestBody = inputMessage.body.bufferedReader().use { it.readLines() }
        val requests = requestBody.map {
            objectMapper.readValue<ToDoRequest>(it)
        }
        return ToDoListRequest(requests)
    }
}
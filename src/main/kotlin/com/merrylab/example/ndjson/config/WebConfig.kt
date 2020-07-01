package com.merrylab.example.ndjson.config

import com.merrylab.example.ndjson.config.converter.TodoListHttpMessageConverter
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

    override fun extendMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        converters.add(TodoListHttpMessageConverter())
    }

}
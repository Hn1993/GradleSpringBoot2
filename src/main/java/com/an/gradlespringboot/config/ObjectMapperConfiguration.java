package com.an.gradlespringboot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 这个类是为了 让 返回值为{} 而不是 null
 * Result return new Result<>(2, msg,new Object());
 */
@Component
public class ObjectMapperConfiguration {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }
}

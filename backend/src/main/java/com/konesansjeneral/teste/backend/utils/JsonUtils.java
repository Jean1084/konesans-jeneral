package com.konesansjeneral.teste.backend.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.web.multipart.MultipartFile;

public class JsonUtils {

    private static final ObjectMapper objectMapper = createObjectMapper();

    public static <T> T readValue(MultipartFile file, Class<T> valueType) {
        try {
            return objectMapper.readValue(file.getBytes(), valueType);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static byte[] writeValueAsBytes(Object value) {
        try {
            return objectMapper.writeValueAsBytes(value);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static ObjectMapper createObjectMapper() {
        var res = new ObjectMapper();
        res.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        res.enable(SerializationFeature.INDENT_OUTPUT);
        return res;
    }
}

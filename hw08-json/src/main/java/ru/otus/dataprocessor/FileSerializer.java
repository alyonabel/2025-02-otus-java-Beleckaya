package ru.otus.dataprocessor;

import java.io.IOException;
import java.util.Map;

public class FileSerializer implements Serializer {
    JacksonObjectMapper jacksonObjectMapper = new JacksonObjectMapper();
    String filename;

    public FileSerializer(String fileName) {
        this.filename = fileName;
    }

    @Override
    public void serialize(Map<String, Double> data) throws IOException {
        jacksonObjectMapper.save(data, filename);
        //формирует результирующий json и сохраняет его в файл
    }
}

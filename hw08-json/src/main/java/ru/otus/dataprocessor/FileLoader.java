package ru.otus.dataprocessor;

import ru.otus.model.Measurement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileLoader implements Loader {
    JacksonObjectMapper jacksonObjectMapper = new JacksonObjectMapper();
    String fileName;

    public FileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() throws IOException {
        return jacksonObjectMapper.loadFromJson(fileName);
    }
}

package ru.otus.dataprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.otus.model.Measurement;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class JacksonObjectMapper {
    private final ObjectMapper mapper = new ObjectMapper();

    public List<Measurement> loadFromJson(String fileName) throws IOException {

        List<Measurement> measurements = Arrays.asList(mapper.readValue(Paths.get(fileName).toFile(), Measurement[].class));
        System.out.println("Measure loaded from the file" + Paths.get(fileName) + ",measure: " + measurements);
        return measurements;
    }

    public void save(Map<String, Double> measurements, String filename) throws IOException {
        mapper.writeValue(Paths.get(filename).toFile(), measurements);
        System.out.println("Measurements saved to the file" + Paths.get(filename));
    }

}



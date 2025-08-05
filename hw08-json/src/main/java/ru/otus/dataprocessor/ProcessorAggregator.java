package ru.otus.dataprocessor;

import ru.otus.model.Measurement;

import java.util.*;
import java.util.stream.Collectors;

public class ProcessorAggregator implements Processor {

    Map<String, Double> groupedMeasurements = new TreeMap<>();

    @Override
    public Map<String, Double> process(List<Measurement> data) {
        groupedMeasurements = data
                .stream()
                .collect(Collectors.groupingBy(Measurement::getName, Collectors.summingDouble(Measurement::getValue)));

        //группирует выходящий список по name, при этом суммирует поля value
        System.out.println("Measurements after grouping" + groupedMeasurements);
        groupedMeasurements = new TreeMap<String, Double>(groupedMeasurements);
        return groupedMeasurements;
    }
}

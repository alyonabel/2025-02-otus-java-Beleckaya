package ru.otus.processor.homework;

import java.time.LocalDateTime;
import java.util.function.Supplier;

public interface TimeProvider extends Supplier<LocalDateTime> {

    LocalDateTime get();
    boolean isEvenSecond();
}

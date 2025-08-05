package ru.otus.processor.homework;

import java.time.LocalDateTime;

public class TimeProviderImpl implements TimeProvider {

    @Override
    public LocalDateTime get() {
        return null;
    }

    public boolean isEvenSecond() {
        return LocalDateTime.now().getSecond() % 2 == 0;
    }

}

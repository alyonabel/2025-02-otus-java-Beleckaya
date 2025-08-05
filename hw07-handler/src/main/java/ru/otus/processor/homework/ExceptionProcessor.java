package ru.otus.processor.homework;

import ru.otus.model.Message;
import ru.otus.processor.Processor;
import java.time.DateTimeException;

public class ExceptionProcessor implements Processor {

    private final TimeProvider timeProvider;

    public ExceptionProcessor(TimeProvider timeProvider) {
        this.timeProvider=timeProvider;
    }

    @Override
    public Message process(Message message) {
        if (timeProvider.isEvenSecond()) {
            throw new DateTimeException("An exception in an even second");
        }
        return message.toBuilder().build();
    }
}

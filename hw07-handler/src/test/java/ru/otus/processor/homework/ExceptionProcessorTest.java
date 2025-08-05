package ru.otus.processor.homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.model.Message;
import ru.otus.processor.Processor;

import java.time.DateTimeException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ExceptionProcessorTest {
    private TimeProvider timeProviderMock;
    private Processor processor;

    @BeforeEach
    void setUp() {
        timeProviderMock = mock(TimeProvider.class);
        processor = new ExceptionProcessor(timeProviderMock);
    }

    @Test
    void processWithException() {
        Message message = new Message.Builder(1L).field1("data1").build();
        when(timeProviderMock.isEvenSecond()).thenReturn(true);
        assertThrows(DateTimeException.class, () -> processor.process(message));
    }

    @Test
    void processWithoutException() {
        Message message = new Message.Builder(1L).field1("data1").build();
        when(timeProviderMock.isEvenSecond()).thenReturn(false);
        assertDoesNotThrow(() -> processor.process(message));
    }
}
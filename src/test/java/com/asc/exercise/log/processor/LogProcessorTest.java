package com.asc.exercise.log.processor;

import com.asc.exercise.log.processor.input.InputProcessor;
import com.asc.exercise.log.processor.model.ProcessingResult;
import com.asc.exercise.log.processor.output.ConsoleOutputWriter;
import com.asc.exercise.log.processor.output.JsonFileOutputWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LogProcessorTest {

    @Mock
    private InputProcessor inputProcessor;

    @Mock
    private ConsoleOutputWriter consoleOutputWriter;

    @Mock
    private JsonFileOutputWriter jsonFileOutputWriter;

    private LogProcessor logProcessor;

    @BeforeEach
    void setUp() {
        logProcessor = new LogProcessor(inputProcessor, consoleOutputWriter, jsonFileOutputWriter);
    }

    @Test
    void shouldProcessInputAndWriteToConsoleAndJson() throws IOException {
        ProcessingResult result = new ProcessingResult(Map.of('b', 2), 1);

        when(inputProcessor.process()).thenReturn(result);

        logProcessor.process();

        verify(inputProcessor).process();
        verify(consoleOutputWriter).write(result);
        verify(jsonFileOutputWriter).write(result);
    }
}

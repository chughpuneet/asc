package com.asc.exercise.log.processor;

import com.asc.exercise.log.processor.input.InputProcessor;
import com.asc.exercise.log.processor.model.ProcessingResult;
import com.asc.exercise.log.processor.output.ConsoleOutputWriter;
import com.asc.exercise.log.processor.output.JsonFileOutputWriter;

import java.io.IOException;

public class LogProcessor {

    private final ConsoleOutputWriter consoleOutputWriter;
    private final JsonFileOutputWriter jsonFileOutputWriter;
    private final InputProcessor inputProcessor;

    public LogProcessor(InputProcessor inputProcessor,
                        ConsoleOutputWriter consoleOutputWriter,
                        JsonFileOutputWriter jsonFileOutputWriter) {
        this.inputProcessor = inputProcessor;
        this.consoleOutputWriter = consoleOutputWriter;
        this.jsonFileOutputWriter = jsonFileOutputWriter;
    }

    public void process() throws IOException {
        ProcessingResult result = inputProcessor.process();

        consoleOutputWriter.write(result);
        jsonFileOutputWriter.write(result);

        System.out.println("Processing completed successfully.");
    }
}

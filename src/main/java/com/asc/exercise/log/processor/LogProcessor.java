package com.asc.exercise.log.processor;

import com.asc.exercise.log.processor.input.InputProcessor;
import com.asc.exercise.log.processor.model.ProcessingResult;
import com.asc.exercise.log.processor.output.OutputWriter;

import java.io.IOException;
import java.util.List;

public class LogProcessor {

    private final InputProcessor inputProcessor;
    private final List<OutputWriter> outputWriters;

    public LogProcessor(InputProcessor inputProcessor, List<OutputWriter> writers) {
        this.inputProcessor = inputProcessor;
        this.outputWriters = writers;
    }

    public void process() throws IOException {
        ProcessingResult result = inputProcessor.process();

        for (OutputWriter outputWriter : outputWriters) {
            outputWriter.write(result);
        }
    }
}

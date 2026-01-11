package com.asc.exercise.log.processor.output;

import com.asc.exercise.log.processor.model.ProcessingResult;

import java.io.IOException;

public abstract class OutputWriter {
    public void write(ProcessingResult result) throws IOException {
        if (!isValidProcessingResult(result)) {
            throw new IllegalArgumentException("Invalid processing result");
        }
        writeOutput(result);
    }

    protected abstract void writeOutput(ProcessingResult result) throws IOException;

    protected boolean isValidProcessingResult(ProcessingResult result) {
        return result != null && result.consonantsCount() != null;
    }
}

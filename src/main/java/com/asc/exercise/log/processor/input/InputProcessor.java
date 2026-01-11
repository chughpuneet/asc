package com.asc.exercise.log.processor.input;

import com.asc.exercise.log.processor.model.ProcessingResult;

import java.io.IOException;

public interface InputProcessor {
    ProcessingResult process() throws IOException;
}

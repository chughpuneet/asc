package com.asc.exercise.log.processor.output;

import com.asc.exercise.log.processor.model.ProcessingResult;

public class ConsoleOutputWriter extends OutputWriter {

    @Override
    public void writeOutput(ProcessingResult result) {
        result.consonantsCount()
                .forEach((character, characterCount) -> System.out.println(character + " : " + characterCount));

        System.out.println("slow bike count: " + result.slowBikeCount());
    }
}

package com.asc.exercise.log.processor.input;

import com.asc.exercise.log.processor.analyzer.TextAnalyzer;
import com.asc.exercise.log.processor.model.ProcessingResult;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextFileInputProcessor implements InputProcessor {
    private final String path;
    private final TextAnalyzer textAnalyzer;

    public TextFileInputProcessor(String path, TextAnalyzer textAnalyzer) {
        this.path = path;
        this.textAnalyzer = textAnalyzer;
    }

    public ProcessingResult process() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String logLine;
            while ((logLine = reader.readLine()) != null) {
                textAnalyzer.analyze(logLine);
            }
        }

        return textAnalyzer.getTextAnalysisResult();
    }
}

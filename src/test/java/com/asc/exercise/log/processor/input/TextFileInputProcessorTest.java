package com.asc.exercise.log.processor.input;

import com.asc.exercise.log.processor.analyzer.TextAnalyzer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TextFileInputProcessorTest {

    @TempDir
    private Path tempDir;

    @Mock
    private TextAnalyzer textAnalyzer;

    @Test
    void shouldReadFileLineWise() throws IOException {
        Path file = tempDir.resolve("input.txt");
        Files.writeString(file, """
                slow bike
                slow bike
                """);

        TextFileInputProcessor processor = new TextFileInputProcessor(file.toString(), textAnalyzer);

        processor.process();

        verify(textAnalyzer, times(2)).analyze("slow bike");
    }

    @Test
    void shouldHandleEmptyFile() throws IOException {
        Path file = tempDir.resolve("empty.txt");
        Files.writeString(file, "");

        TextFileInputProcessor processor =
                new TextFileInputProcessor(file.toString(), textAnalyzer);

        processor.process();

        verify(textAnalyzer, never()).analyze(anyString());
    }

}

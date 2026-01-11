package com.asc.exercise.log.processor.output;

import com.asc.exercise.log.processor.model.ProcessingResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConsoleOutputWriterTest {

    private ConsoleOutputWriter consoleOutputWriter;
    private ByteArrayOutputStream outputStream;
    private final PrintStream originalStdout = System.out;

    @BeforeEach
    void setUp() {
        consoleOutputWriter = new ConsoleOutputWriter();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalStdout);
    }

    @Test
    void shouldThrowExceptionForNullResult() {
        assertThatThrownBy(() -> consoleOutputWriter.write(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid processing result");
    }

    @Test
    void shouldPrintConsonantCountsAndSlowBikeCount() throws IOException {
        ProcessingResult result = new ProcessingResult(
                Map.of('b', 2, 'c', 1),
                3
        );

        consoleOutputWriter.write(result);

        String output = outputStream.toString();

        assertThat(output)
                .contains("b : 2")
                .contains("c : 1")
                .contains("slow bike count: 3");
    }

    @Test
    void shouldHandleEmptyResult() throws IOException {
        ProcessingResult result = new ProcessingResult(Map.of(), 0);

        consoleOutputWriter.write(result);

        String output = outputStream.toString();

        assertThat(output)
                .doesNotContain(" : ")
                .contains("slow bike count: 0");
    }
}

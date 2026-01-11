package com.asc.exercise.log.processor.output;

import com.asc.exercise.log.processor.model.ProcessingResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JsonFileOutputWriterTest {

    @TempDir
    Path tempDir;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldThrowExceptionForNullResult() {
        JsonFileOutputWriter writer = new JsonFileOutputWriter("dummy.json");

        assertThatThrownBy(() -> writer.write(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid processing result");
    }

    @Test
    void shouldWriteJsonFileWithConsonantsAndSlowBikeCount() throws IOException {
        Path outputFile = tempDir.resolve("output.json");

        ProcessingResult result = new ProcessingResult(
                Map.of('b', 2, 'c', 1),
                3
        );

        JsonFileOutputWriter writer = new JsonFileOutputWriter(outputFile.toString());

        writer.write(result);

        String content = Files.readString(outputFile);

        JsonNode actual = objectMapper.readTree(content);

        assertThat(actual.get("slowBikeCount").asInt()).isEqualTo(3);
        assertThat(actual.get("consonants").get("b").asInt()).isEqualTo(2);
        assertThat(actual.get("consonants").get("c").asInt()).isEqualTo(1);
    }

    @Test
    void shouldWriteJsonFileWithEmptyConsonants() throws IOException {
        Path outputFile = tempDir.resolve("output.json");

        ProcessingResult result = new ProcessingResult(Map.of(), 0);

        JsonFileOutputWriter writer = new JsonFileOutputWriter(outputFile.toString());

        writer.write(result);

        JsonNode actual = objectMapper.readTree(Files.readString(outputFile));

        assertThat(actual.get("slowBikeCount").asInt()).isEqualTo(0);
        assertThat(actual.get("consonants")).isEmpty();
    }
}

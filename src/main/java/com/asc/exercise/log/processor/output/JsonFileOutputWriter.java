package com.asc.exercise.log.processor.output;

import com.asc.exercise.log.processor.model.ProcessingResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

public class JsonFileOutputWriter extends OutputWriter {

    private final String filePath;
    private final ObjectMapper objectMapper;

    public JsonFileOutputWriter(String filePath) {
        this.filePath = filePath;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void writeOutput(ProcessingResult result) throws IOException {
        ObjectNode root = objectMapper.createObjectNode();
        root.put("slowBikeCount", result.slowBikeCount());

        ObjectNode consonantsNode = objectMapper.createObjectNode();
        result.consonantsCount()
                .forEach((consonant, consonantCount) -> consonantsNode.put(String.valueOf(consonant), consonantCount));

        root.set("consonants", consonantsNode);

        objectMapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(filePath), root);
    }
}

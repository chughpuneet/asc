package com.asc.exercise.log.processor.analyzer;

import com.asc.exercise.log.processor.model.ProcessingResult;

import java.util.HashMap;
import java.util.Map;

public class TextAnalyzer {

    private static final String VOWELS = "aeiou";
    private static final String PHRASE_SLOW_BIKE = "slow bike";

    private final Map<Character, Integer> consonantsCount = new HashMap<>();
    private int slowBikeCount = 0;

    public void analyze(String input) {
        if (input == null) {
            return;
        }

        String lowerCaseInput = input.toLowerCase();
        countSlowBikePhrase(lowerCaseInput);
        countConsonants(lowerCaseInput);
    }

    public ProcessingResult getTextAnalysisResult() {
        return new ProcessingResult(Map.copyOf(consonantsCount), slowBikeCount);
    }

    private void countSlowBikePhrase(String text) {
        int index = 0;
        while (index != -1) {
            index = text.indexOf(PHRASE_SLOW_BIKE, index);
            if (index != -1) {
                slowBikeCount++;
                index += PHRASE_SLOW_BIKE.length();
            }
        }
    }

    private void countConsonants(String text) {
        for (char textChar : text.toCharArray()) {
            if (textChar >= 'a' && textChar <= 'z' && VOWELS.indexOf(textChar) == -1) {
                consonantsCount.merge(textChar, 1, Integer::sum);
            }
        }
    }
}

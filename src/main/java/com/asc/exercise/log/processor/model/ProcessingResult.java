package com.asc.exercise.log.processor.model;

import java.util.Map;

public record ProcessingResult(Map<Character, Integer> consonantsCount,
                               int slowBikeCount) {
}

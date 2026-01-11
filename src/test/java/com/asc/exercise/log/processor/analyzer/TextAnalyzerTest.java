package com.asc.exercise.log.processor.analyzer;

import com.asc.exercise.log.processor.model.ProcessingResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TextAnalyzerTest {

    private TextAnalyzer analyzer;

    @BeforeEach
    void setUp() {
        analyzer = new TextAnalyzer();
    }

    @Test
    void shouldProduceEmptyResultForNullString() {
        analyzer.analyze(null);

        ProcessingResult result = analyzer.getTextAnalysisResult();

        assertThat(result.consonantsCount()).isEmpty();
        assertThat(result.slowBikeCount()).isZero();
    }

    @Test
    void shouldIgnoreVowelsAndCountOnlyConsonants() {
        analyzer.analyze("aAbBcCdDeEfFgGhHiIjJkKlLmMoOuU");

        ProcessingResult result = analyzer.getTextAnalysisResult();

        assertThat(result.consonantsCount())
                .containsEntry('b', 2)
                .containsEntry('c', 2)
                .containsEntry('d', 2)
                .containsEntry('f', 2)
                .containsEntry('g', 2)
                .containsEntry('h', 2)
                .containsEntry('j', 2)
                .containsEntry('k', 2)
                .containsEntry('l', 2)
                .containsEntry('m', 2)
                .doesNotContainKeys('a', 'e', 'i', 'o', 'u');
    }

    @Test
    void shouldBeCaseInsensitiveWhenCountingConsonants() {
        TextAnalyzer analyzer = new TextAnalyzer();

        analyzer.analyze("BbCc");

        ProcessingResult result = analyzer.getTextAnalysisResult();

        assertThat(result.consonantsCount())
                .containsEntry('b', 2)
                .containsEntry('c', 2);
    }

    @Test
    void shouldNotCountOtherCharacters() {
        TextAnalyzer analyzer = new TextAnalyzer();

        analyzer.analyze("BbCc,. _() aA");

        ProcessingResult result = analyzer.getTextAnalysisResult();

        assertThat(result.consonantsCount().size()).isEqualTo(2);
        assertThat(result.consonantsCount())
                .containsEntry('b', 2)
                .containsEntry('c', 2);
    }

    @Test
    void shouldCountSlowBikeOccurrencesCaseInsensitively() {
        TextAnalyzer analyzer = new TextAnalyzer();

        analyzer.analyze("Slow bike slow BIKE SLOW Bike");

        ProcessingResult result = analyzer.getTextAnalysisResult();

        assertThat(result.slowBikeCount()).isEqualTo(3);
    }

    @Test
    void shouldNotCountPartialSlowBikeMatches() {
        TextAnalyzer analyzer = new TextAnalyzer();

        analyzer.analyze("slow bik slowbike slow-bbike");

        ProcessingResult result = analyzer.getTextAnalysisResult();

        assertThat(result.slowBikeCount()).isZero();
    }

    @Test
    void shouldAccumulateResultsAcrossMultipleAnalyzeCalls() {
        TextAnalyzer analyzer = new TextAnalyzer();

        analyzer.analyze("slow bike");
        analyzer.analyze("slow bike");

        ProcessingResult result = analyzer.getTextAnalysisResult();

        assertThat(result.slowBikeCount()).isEqualTo(2);
        assertThat(result.consonantsCount().get('s')).isEqualTo(2);
        assertThat(result.consonantsCount().get('l')).isEqualTo(2);
        assertThat(result.consonantsCount().get('w')).isEqualTo(2);
        assertThat(result.consonantsCount().get('b')).isEqualTo(2);
        assertThat(result.consonantsCount().get('k')).isEqualTo(2);
    }

    @Test
    void shouldReturnEmptyCountIfTextContainsOnlyVowels() {
        TextAnalyzer analyzer = new TextAnalyzer();
        analyzer.analyze("a");
        ProcessingResult result = analyzer.getTextAnalysisResult();
        assertEquals(0, result.slowBikeCount());
        assertTrue(result.consonantsCount().isEmpty());
    }
}

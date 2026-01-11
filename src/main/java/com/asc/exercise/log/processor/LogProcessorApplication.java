package com.asc.exercise.log.processor;

import com.asc.exercise.log.processor.analyzer.TextAnalyzer;
import com.asc.exercise.log.processor.input.TextFileInputProcessor;
import com.asc.exercise.log.processor.output.ConsoleOutputWriter;
import com.asc.exercise.log.processor.output.JsonFileOutputWriter;

import java.util.Scanner;

public class LogProcessorApplication {

    public static void main(String[] args) {
        LogProcessor processor = null;
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter input text file path: ");
            String inputFilePath = scanner.nextLine();

            System.out.print("Enter output JSON file path: ");
            String outputFilePath = scanner.nextLine();

            processor = new LogProcessor(new TextFileInputProcessor(inputFilePath, new TextAnalyzer()),
                    new ConsoleOutputWriter(),
                    new JsonFileOutputWriter(outputFilePath));
            processor.process();

        } catch (Exception e) {
            System.err.println("Error during processing: " + e.getMessage());
        }
    }
}

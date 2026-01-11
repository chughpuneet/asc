package com.asc.exercise.log.processor;

import com.asc.exercise.log.processor.analyzer.TextAnalyzer;
import com.asc.exercise.log.processor.input.TextFileInputProcessor;
import com.asc.exercise.log.processor.output.ConsoleOutputWriter;
import com.asc.exercise.log.processor.output.JsonFileOutputWriter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class LogProcessorApplication {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter input text file path: ");
            Path inputPath = Path.of(scanner.nextLine());

            if (Files.notExists(inputPath) || !Files.isRegularFile(inputPath)) {
                System.err.println("Invalid input file path: " + inputPath);
                return;
            }

            System.out.print("Enter output JSON file path: ");
            String outputPathStr = scanner.nextLine();

            if (null == outputPathStr || outputPathStr.isEmpty()) {
                System.err.println("Invalid output file path: " + outputPathStr);
                return;
            }
            Path outputPath = Path.of(outputPathStr);

            LogProcessor processor = new LogProcessor(
                    new TextFileInputProcessor(inputPath.toString(), new TextAnalyzer()),
                    new ConsoleOutputWriter(),
                    new JsonFileOutputWriter(outputPath.toString())
            );

            processor.process();
            System.out.println("Processing completed successfully.");

        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}

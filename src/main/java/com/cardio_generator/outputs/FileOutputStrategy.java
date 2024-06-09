package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Generates the file of patients' record
 */
//changed class name to upper camel case
public class FileOutputStrategy implements OutputStrategy {

    //changed variable name to lower camel case
    private String baseDirectory;

    //changed variable name to lower camel case
    public final ConcurrentHashMap<String, String> fileMap = new ConcurrentHashMap<>();

    /**
     * construct the object
     * @param baseDirectory the target directory for the file
     */
    //changed constructor's name to the same as the class name
    public FileOutputStrategy(String baseDirectory) {
        //changed variable name to lower camel case
        this.baseDirectory = baseDirectory;
    }

    /**
     * Generates the file at the target folder
     * @param patientId the ID number of a patient
     * @param timestamp the time of the record
     * @param label the label of patient
     * @param data the data of patient
     */
    @Override
    //Changed parameter name timestamp to lower camel case
    public void output(int patientId, long timeStamp, String label, String data) {
        try {
            // Create the directory
            Files.createDirectories(Paths.get(baseDirectory));
        } catch (IOException e) {
            System.err.println("Error creating base directory: " + e.getMessage());
            return;
        }
        // Set the FilePath variable
        //changed variable name to lower camel case
        String filePath = fileMap.computeIfAbsent(label, k -> Paths.get(baseDirectory, label + ".txt").toString());

        // Write the data to the file
        try (PrintWriter out = new PrintWriter(
                Files.newBufferedWriter(Paths.get(filePath), StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {
            out.printf("Patient ID: %d, Timestamp: %d, Label: %s, Data: %s%n", patientId, timeStamp, label, data);
        } catch (Exception e) {
            System.err.println("Error writing to file " + filePath + ": " + e.getMessage());
        }
    }
}
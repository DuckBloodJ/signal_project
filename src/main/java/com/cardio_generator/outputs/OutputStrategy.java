package com.cardio_generator.outputs;

/**
 * Provide the interface for output the patients' record
 */
public interface OutputStrategy {
    void output(int patientId, long timestamp, String label, String data);
}

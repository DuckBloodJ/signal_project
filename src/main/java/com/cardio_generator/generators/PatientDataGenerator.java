package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * Provide the interface for a patient's data generator
 */
public interface PatientDataGenerator {
    void generate(int patientId, OutputStrategy outputStrategy);
}

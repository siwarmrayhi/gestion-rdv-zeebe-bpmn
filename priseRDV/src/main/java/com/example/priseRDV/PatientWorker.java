package com.example.priseRDV;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class PatientWorker {

    @JobWorker(type = "verify-patient", autoComplete = false)
    public void verifyPatient(JobClient client, ActivatedJob job) {
        Map<String, Object> variables = job.getVariablesAsMap();
        String patientId = (String) variables.get("patientId");

        // Simulation : patient valide si ID non vide
        boolean patientValid = patientId != null && !patientId.isEmpty();

        Map<String, Object> result = new HashMap<>();
        result.put("patientValid", patientValid);

        client.newCompleteCommand(job.getKey())
                .variables(result)
                .send()
                .join();

        System.out.println("✅ VerifyPatient → patientValid = " + patientValid);
    }
}
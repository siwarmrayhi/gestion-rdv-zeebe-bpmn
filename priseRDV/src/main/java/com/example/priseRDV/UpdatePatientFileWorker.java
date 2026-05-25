package com.example.priseRDV;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class UpdatePatientFileWorker {
    @JobWorker(type = "update-patient-file", autoComplete = false)
    public void updatePatientFile(JobClient client, ActivatedJob job) {
        Map<String, Object> variables = job.getVariablesAsMap();
        System.out.println("📋 MAJ dossier patient : " + variables.get("patientId"));

        client.newCompleteCommand(job.getKey())
                .send()
                .join();
    }
}

package com.example.priseRDV;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class InsuranceWorker {

    @JobWorker(type = "check-insurance" ,autoComplete = false)
    public void checkInsurance(JobClient client, ActivatedJob job) {
        Map<String, Object> variables = job.getVariablesAsMap();
        String patientId = (String) variables.get("patientId");

        // Simulation : assurance valide
        boolean assuranceValide = true;

        Map<String, Object> result = new HashMap<>();
        result.put("assuranceValide", assuranceValide);

        client.newCompleteCommand(job.getKey())
                .variables(result)
                .send()
                .join();

        System.out.println("✅ CheckInsurance → assuranceValide = " + assuranceValide);
    }
}
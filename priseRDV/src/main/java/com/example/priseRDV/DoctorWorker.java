package com.example.priseRDV;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class DoctorWorker {

    @JobWorker(type = "find-doctor", autoComplete = false)
    public void findDoctor(JobClient client, ActivatedJob job) {
        Map<String, Object> variables = job.getVariablesAsMap();
        String specialite = (String) variables.get("specialite");

        // Simulation : médecin disponible
        boolean medecinDisponible = true;
        String medecinId = "DR-001";

        Map<String, Object> result = new HashMap<>();
        result.put("medecinDisponible", medecinDisponible);
        result.put("medecinId", medecinId);

        client.newCompleteCommand(job.getKey())
                .variables(result)
                .send()
                .join();

        System.out.println("✅ FindDoctor → médecin = " + medecinId);
    }
}
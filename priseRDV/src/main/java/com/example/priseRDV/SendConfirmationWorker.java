package com.example.priseRDV;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class SendConfirmationWorker {
    @JobWorker(type = "send-confirmation", autoComplete = false)
    public void sendConfirmation(JobClient client, ActivatedJob job) {
        Map<String, Object> variables = job.getVariablesAsMap();
        String patientId = (String) variables.get("patientId");
        String appointmentId = (String) variables.get("appointmentId");

        // Simulation envoi email
        System.out.println("📧 Confirmation envoyée au patient "
                + patientId + " pour RDV " + appointmentId);

        client.newCompleteCommand(job.getKey())
                .send()
                .join();
    }
}

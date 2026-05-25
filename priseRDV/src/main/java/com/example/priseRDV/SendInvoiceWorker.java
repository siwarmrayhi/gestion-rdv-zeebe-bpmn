package com.example.priseRDV;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class SendInvoiceWorker {
    @JobWorker(type = "send-invoice", autoComplete = false)
    public void sendInvoice(JobClient client, ActivatedJob job) {
        Map<String, Object> variables = job.getVariablesAsMap();
        String factureId = (String) variables.get("factureId");
        String patientId = (String) variables.get("patientId");

        System.out.println("📨 Facture " + factureId
                + " envoyée au patient " + patientId);

        client.newCompleteCommand(job.getKey())
                .send()
                .join();
    }
}

package com.example.priseRDV;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class GenerateInvoiceWorker {
    @JobWorker(type = "generate-invoice", autoComplete = false)
    public void generateInvoice(JobClient client, ActivatedJob job) {
        Map<String, Object> variables = job.getVariablesAsMap();
        Double montant = (Double) variables.get("montantFacture");

        String factureId = "FAC-" + System.currentTimeMillis();

        Map<String, Object> result = new HashMap<>();
        result.put("factureId", factureId);
        result.put("montantFacture", montant != null ? montant : 50.0);

        client.newCompleteCommand(job.getKey())
                .variables(result)
                .send()
                .join();

        System.out.println("🧾 Facture générée : " + factureId);
    }
}

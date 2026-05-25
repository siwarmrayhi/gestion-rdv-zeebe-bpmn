package com.example.priseRDV;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class CancelRefundWorker {
    @JobWorker(type = "cancel-refund", autoComplete = false)
    public void cancelRefund(JobClient client, ActivatedJob job) {
        System.out.println("❌ RDV annulé et remboursé");

        client.newCompleteCommand(job.getKey())
                .send()
                .join();
    }
}

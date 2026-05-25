package com.example.priseRDV;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class RegisterAppointmentWorker {
    @JobWorker(type = "register-appointment" , autoComplete = false)
    public void registerAppointment(JobClient client, ActivatedJob job) {
        Map<String, Object> variables = job.getVariablesAsMap();

        // Génération d'un ID de RDV
        String appointmentId = "RDV-" + System.currentTimeMillis();

        Map<String, Object> result = new HashMap<>();
        result.put("appointmentId", appointmentId);

        client.newCompleteCommand(job.getKey())
                .variables(result)
                .send()
                .join();

        System.out.println("✅ RegisterAppointment → ID = " + appointmentId);
    }
}

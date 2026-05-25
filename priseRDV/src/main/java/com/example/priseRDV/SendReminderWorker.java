package com.example.priseRDV;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class SendReminderWorker {

    // On utilise autoComplete = true (c'est la valeur par défaut si on ne met rien)
    // On enlève le JobClient des paramètres, on n'en a plus besoin !
    @JobWorker(type = "send-reminder", autoComplete = true)
    public void sendReminder(ActivatedJob job) {

        System.out.println("🔔 REMINDER CALLED - job: " + job.getKey());

System.out.println("Envoi du SMS au patient...");

        System.out.println("🔔 FIN DE LA METHODE - Spring Boot va valider la tâche automatiquement !");
    }
}
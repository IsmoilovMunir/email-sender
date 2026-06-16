package com.tasktracker.email_sender.listener;

import com.tasktracker.email_sender.dto.EmailEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailListener {

    private final JavaMailSender mailSender;
    @KafkaListener(topics = "EMAIL_SENDING_TASKS", groupId = "email-sender-group")
    public void listen(EmailEventDto event){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("task-tracker@ravenda.tech");
            message.setTo(event.getTo());
            message.setSubject(event.getSubject());
            message.setText(event.getBody());
            mailSender.send(message);
            System.out.println("Email sent to: " + event.getTo());
        }catch (Exception e){
            System.out.println("FAILED TO SEND EMAIL: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

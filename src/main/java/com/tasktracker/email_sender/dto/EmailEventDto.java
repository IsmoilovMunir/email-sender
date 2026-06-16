package com.tasktracker.email_sender.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailEventDto {
    private String to;
    private String subject;
    private String body;
}

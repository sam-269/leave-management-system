package com.projects.leavemanagementsystem.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class ApplicationParams {
    private DateParams duration;
    private String reason;
    private String type;
}

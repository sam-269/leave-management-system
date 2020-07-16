package com.projects.leavemanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projects.leavemanagementsystem.customvalidations.AfterPresentDate;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Component
@Data
@SuppressWarnings("unused")
public class DateParams {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @NotNull(message = "Date cannot be null")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @NotNull(message = "Date cannot be null")
    private Date endDate;
}

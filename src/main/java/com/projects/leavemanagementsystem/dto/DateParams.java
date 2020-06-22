package com.projects.leavemanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projects.leavemanagementsystem.customvalidations.AfterPresentDate;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Component
@SuppressWarnings("unused")
public class DateParams {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @NotNull(message = "Date cannot be null")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @NotNull(message = "Date cannot be null")
    private Date endDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date fromDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @AfterPresentDate(message = "Date cannot be a past date")
    private Date toDate;

    public Date getStartdate() {
        return startDate;
    }

    public void setStartdate(Date startdate) {
        this.startDate = startdate;
    }

    public Date getEnddate() {
        return endDate;
    }

    public void setEnddate(Date enddate) {
        this.endDate = enddate;
    }

   // @AfterDate(message = "Date cannot be a past date")
    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

   // @AfterDate(message = "Date cannot be a past date")
    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}

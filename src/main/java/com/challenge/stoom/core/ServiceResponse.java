package com.challenge.stoom.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private Serializable payload;
    private String exceptionMessage;
    private String friedlyMessage;
    private String date;

    public void setDate(){
        this.date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    }
}

package com.challenge.stoom.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class GeocodeLocation implements Serializable {
    private static final long serialVersionUID = 1L;

    private String latitude;
    private String longitude;
}

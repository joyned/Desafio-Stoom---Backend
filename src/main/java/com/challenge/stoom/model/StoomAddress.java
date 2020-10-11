package com.challenge.stoom.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class StoomAddress implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotNull(message = "Nome da rua não pode ser nulo.")
    private String streetName;
    @NotNull(message = "Número não ser nulo.")
    private Integer number;
    private String complement;
    @NotNull(message = "Bairro não ser nulo.")
    private String neighbourhood;
    @NotNull(message = "Cidade não ser nulo.")
    private String city;
    @NotNull(message = "Estado não ser nulo.")
    private String state;
    @NotNull(message = "Pais não ser nulo.")
    private String country;
    @NotNull(message = "CEP não ser nulo.")
    private String zipCode;
    private String latitude;
    private String longitude;
}

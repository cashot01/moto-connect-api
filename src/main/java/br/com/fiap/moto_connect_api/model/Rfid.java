package br.com.fiap.moto_connect_api.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class Rfid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "nome Area requirida")
    private String nomeArea;

    @NotBlank(message = "latitude requerida")
    private String latidude;

    @NotBlank(message = "longitude requirida")
    private String longitude;


}

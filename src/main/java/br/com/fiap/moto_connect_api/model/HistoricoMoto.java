package br.com.fiap.moto_connect_api.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class HistoricoMoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "parte obrigatoria")
    private String parte; // hidraulica, eletrica, freios, motor

    @NotBlank(message = "descrição obrigatoria")
    private String descricao;
}

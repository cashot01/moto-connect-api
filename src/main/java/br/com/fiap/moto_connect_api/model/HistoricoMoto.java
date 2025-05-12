package br.com.fiap.moto_connect_api.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class HistoricoMoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String parte; // hidraulica, eletrica, freios, motor

    private String descricao;
}

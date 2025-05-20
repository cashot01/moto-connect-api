package br.com.fiap.moto_connect_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_historico_moto")
public class HistoricoMoto  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Parte obrigatória")
    private String parte; // hidráulica, elétrica, freios, motor

    @NotBlank(message = "Descrição obrigatória")
    private String descricao;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "moto_id", nullable = false)
    //@JsonBackReference
    //private Moto moto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moto_id", nullable = false)
    @JsonBackReference
    private Moto moto;
}

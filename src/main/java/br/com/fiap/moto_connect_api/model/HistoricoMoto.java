package br.com.fiap.moto_connect_api.model;

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
public class HistoricoMoto extends Moto{

    @Override
    public String getPlaca() {
        return super.getPlaca();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "parte obrigatoria")
    private String parte; // hidraulica, eletrica, freios, motor

    @NotBlank(message = "descrição obrigatoria")
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moto_id")
    private Moto moto;
}

package br.com.fiap.moto_connect_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_moto")
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotBlank(message = "tagRfid obrigatória")
    //private String tagRfid;

    @NotBlank(message = "modelo da moto obrigatório")
    private String modelo;

    @NotBlank(message = "placa obrigatória")
    @Pattern(regexp = "^([A-Z]{3}[0-9][A-Z][0-9]{2}|[A-Z]{3}[0-9]{4})$")
    private String placa;

    @NotBlank(message = "Data obrigatoria")
    private LocalDateTime dataCadastro;

    private TipoStatus status; // em manutenção ou pronta

    @OneToMany(mappedBy = "moto", cascade = CascadeType.ALL)
    private List<Localizacao> localizacoes = new ArrayList<>();

}

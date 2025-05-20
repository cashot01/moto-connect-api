package br.com.fiap.moto_connect_api.model;

import br.com.fiap.moto_connect_api.repository.HistoricoMotoRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_moto")
public class Moto  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    //@NotBlank(message = "tagRfid obrigatória")
    //private String tagRfid;

    @NotNull(message = "modelo da moto obrigatório")
    private TipoModelo modelo;

    @NotBlank(message = "placa obrigatória")
    @Pattern(regexp = "^([A-Z]{3}[0-9][A-Z][0-9]{2}|[A-Z]{3}[0-9]{4})$")
    private String placa;

    @NotNull(message = "Data obrigatoria")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "A data de cadastro não pode ser futura")
    private LocalDate dataCadastro;

    @NotNull(message = "status obrigatorio")
    private TipoStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tb_rfid", referencedColumnName = "id")
    private Rfid rfid;

    //@OneToMany(cascade = CascadeType.ALL)
    /*@JoinColumn(name = "tb_historico_moto", referencedColumnName = "id")
    private HistoricoMoto historicoMoto;

     */

    @OneToMany(mappedBy = "moto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<HistoricoMoto> historicos = new ArrayList<>();

   /*@OneToMany(mappedBy = "moto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistoricoMoto> historicos = new ArrayList<>();*/



}

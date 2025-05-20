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
@Table(name = "tb_rfid")
public class Rfid  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "nome da área requerida")
    private String nomeArea;

    @NotBlank(message = "latitude requerida")
    private String latitude;

    @NotBlank(message = "longitude requerida")
    private String longitude;


     @OneToOne(mappedBy = "rfid")
     @JsonBackReference
     private Moto moto;
}


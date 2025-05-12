package br.com.fiap.moto_connect_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "nome usuario obrigatorio")
    private String nome;

    @Email(message = "email invalido")
    @NotBlank(message = "email obrigatorio")
    private String email;

    @NotBlank(message = "senha origatoria")
    private String senha;

}

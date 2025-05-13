package br.com.fiap.moto_connect_api.config;

import br.com.fiap.moto_connect_api.model.Moto;
import br.com.fiap.moto_connect_api.model.TipoModelo;
import br.com.fiap.moto_connect_api.model.Usuario;
import br.com.fiap.moto_connect_api.repository.MotoRepository;
import br.com.fiap.moto_connect_api.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static br.com.fiap.moto_connect_api.model.TipoStatus.MANUTENCAO;

@Component
public class DatabaseSeeder {
    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

   /* @PostConstruct
    public void motoSeed() {
        motoRepository.saveAll(
                List.of(
                        Moto.builder().modelo(TipoModelo.SPORT).placa("aaa1234").dataCadastro("2025-05-12").build(),
                        Moto.builder().modelo(TipoModelo.E).placa("bbb1234").dataCadastro("2025-05-12").build(),
                        Moto.builder().modelo(TipoModelo.POP).placa("ccc1234").dataCadastro("2025-05-12").build()));
    }

    */

    @PostConstruct
    public void init() {
        var usuarios = List.of(
                Usuario.builder().nome("Cauan").email("cauan@mottu.com").senha("senha1234").build());


        usuarioRepository.saveAll(usuarios);

        var motos = List.of(
                Moto.builder().modelo(TipoModelo.SPORT).placa("AAA1234").dataCadastro(LocalDate.parse("2025-01-20")).status(MANUTENCAO).build(),
                Moto.builder().modelo(TipoModelo.POP).placa("BBB1234").dataCadastro(LocalDate.parse("2025-02-22")).status(MANUTENCAO).build(),
                Moto.builder().modelo(TipoModelo.E).placa("CCC1234").dataCadastro(LocalDate.parse("2025-03-30")).status(MANUTENCAO).build()
        );

        motoRepository.saveAll(motos);
    }

}

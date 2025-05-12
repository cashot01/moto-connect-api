package br.com.fiap.moto_connect_api.config;

import br.com.fiap.moto_connect_api.model.Moto;
import br.com.fiap.moto_connect_api.model.TipoModelo;
import br.com.fiap.moto_connect_api.model.Usuario;
import br.com.fiap.moto_connect_api.repository.MotoRepository;
import br.com.fiap.moto_connect_api.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
    }

}

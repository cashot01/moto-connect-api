package br.com.fiap.moto_connect_api.config;

import br.com.fiap.moto_connect_api.model.*;
import br.com.fiap.moto_connect_api.repository.HistoricoMotoRepository;
import br.com.fiap.moto_connect_api.repository.MotoRepository;
import br.com.fiap.moto_connect_api.repository.RfidRepository;
import br.com.fiap.moto_connect_api.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static br.com.fiap.moto_connect_api.model.TipoStatus.MANUTENCAO;
import static br.com.fiap.moto_connect_api.model.TipoStatus.REVISADA;

@Component
public class DatabaseSeeder {
    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RfidRepository rfidRepository;

    @Autowired
    private HistoricoMotoRepository historicoMotoRepository;

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
                Moto.builder().modelo(TipoModelo.E).placa("CCC1234").dataCadastro(LocalDate.parse("2025-03-30")).status(REVISADA).build()
        );

        motoRepository.saveAll(motos);

        var rfid = List.of(
                Rfid.builder().latidude("12345").longitude("54321").nomeArea("Area dos Freios").build(),
                Rfid.builder().latidude("56789").longitude("98765").nomeArea("Area Balanceamento Pneus").build(),
                Rfid.builder().latidude("02468").longitude("86420").nomeArea("Area Eletrica").build()
        );

        rfidRepository.saveAll(rfid);

        var historicos = List.of(
                HistoricoMoto.builder().parte("Freios").descricao("Troca da pastilha freio").build(),
                HistoricoMoto.builder().parte("Pneus").descricao("Alinhamento dos Pneus").build(),
                HistoricoMoto.builder().parte("Bateria").descricao("Troca da bateria").build()
        );

        historicoMotoRepository.saveAll(historicos);
    }

}

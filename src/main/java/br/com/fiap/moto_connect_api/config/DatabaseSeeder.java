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

    @PostConstruct
    public void init() {
        var usuarios = List.of(
                Usuario.builder().nome("Cauan").email("cauan@mottu.com").senha("senha1234").build(),
                Usuario.builder().nome("Mateus").email("mateus@mottu.com").senha("passworld5678").build(),
                Usuario.builder().nome("Lucas").email("lucas@mottu.com").senha("098765senha").build()

        );


        usuarioRepository.saveAll(usuarios);

        var motos = List.of(
                Moto.builder().modelo(TipoModelo.SPORT).placa("AAA1234").dataCadastro(LocalDate.parse("2025-01-20")).status(MANUTENCAO).build(),
                Moto.builder().modelo(TipoModelo.POP).placa("BBB1234").dataCadastro(LocalDate.parse("2025-02-22")).status(MANUTENCAO).build(),
                Moto.builder().modelo(TipoModelo.E).placa("CCC1234").dataCadastro(LocalDate.parse("2025-03-30")).status(REVISADA).build()
        );

        motoRepository.saveAll(motos);

        var rfid = List.of(
                Rfid.builder().latitude("12345").longitude("54321").nomeArea("Area dos Freios").build(),
                Rfid.builder().latitude("56789").longitude("98765").nomeArea("Area Balanceamento Pneus").build(),
                Rfid.builder().latitude("02468").longitude("86420").nomeArea("Area Eletrica").build()
        );

        rfidRepository.saveAll(rfid);

        // Agora associe nas motos
        motos.get(0).setRfid(rfid.get(0));
        motos.get(1).setRfid(rfid.get(1));
        motos.get(2).setRfid(rfid.get(2));

        // E salve novamente as motos já com os Rfids associados
        motoRepository.saveAll(motos);

        var historicos = List.of(
                HistoricoMoto.builder().parte("Freios").descricao("Troca da pastilha freio").moto(motos.get(0)).build(),
                HistoricoMoto.builder().parte("Pneus").descricao("Alinhamento dos Pneus").moto(motos.get(1)).build(),
                HistoricoMoto.builder().parte("Bateria").descricao("Troca da bateria").moto(motos.get(2)).build()
        );

        historicoMotoRepository.saveAll(historicos);
    }

}

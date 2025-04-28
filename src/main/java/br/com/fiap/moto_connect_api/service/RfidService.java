package br.com.fiap.moto_connect_api.service;

import br.com.fiap.moto_connect_api.model.Localizacao;
import br.com.fiap.moto_connect_api.model.Moto;
import br.com.fiap.moto_connect_api.repository.LocalizacaoRepository;
import br.com.fiap.moto_connect_api.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RfidService {
    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    public Localizacao registrarLeitura(String tagRfid, Long leitorId) {
        Moto moto = motoRepository.findByTagRfid(tagRfid)
                .orElseThrow(() -> new RuntimeException("Moto não encontrada"));

        Localizacao novaLocalizacao = new Localizacao();
        novaLocalizacao.setMoto(moto);
        novaLocalizacao.setLeitor(leitorRepository.findById(leitorId).get());
        novaLocalizacao.setDataHora(LocalDateTime.now());

        return localizacaoRepository.save(novaLocalizacao);
    }
}

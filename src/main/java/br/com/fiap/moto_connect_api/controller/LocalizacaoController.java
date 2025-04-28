package br.com.fiap.moto_connect_api.controller;

import br.com.fiap.moto_connect_api.model.Localizacao;
import br.com.fiap.moto_connect_api.service.RfidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/localizacoes")
public class LocalizacaoController {
    @Autowired
    private RfidService rfidService;

    @PostMapping
    public ResponseEntity<Localizacao> registrarLeitura(
            @RequestParam String tagRfid,
            @RequestParam Long leitorId
    ) {
        Localizacao localizacao = rfidService.registrarLeitura(tagRfid, leitorId);
        return ResponseEntity.ok(localizacao);
    }

    @GetMapping("/moto/{motoId}")
    public List<Localizacao> getHistoricoPorMoto(@PathVariable Long motoId) {
        return LocalizacaoRepository.findByMotoIdOrderByDataHoraDesc(motoId);
    }
}

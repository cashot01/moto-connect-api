package br.com.fiap.moto_connect_api.controller;

import br.com.fiap.moto_connect_api.model.HistoricoMoto;
import br.com.fiap.moto_connect_api.model.Moto;
import br.com.fiap.moto_connect_api.repository.HistoricoMotoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/motos/historicos")
@Slf4j
public class HistoricoMotoController {

    @Autowired
    private HistoricoMotoRepository historicoMotoRepository;

    @GetMapping
    @Cacheable("historicos")
    @Operation(description = "Listar todos os histricos", tags = "historicos", summary = "Lista dos historicos")
    public List<HistoricoMoto> index() {
        log.info("Buscando todas as MOTOS");
        return historicoMotoRepository.findAll();
    }

    @PostMapping
    @CacheEvict(value = "historicos", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(responses = {
            @ApiResponse(responseCode = "400", description = "Falha na validação")
    })
    public HistoricoMoto create(@RequestBody @Valid HistoricoMoto historicoMoto) {
        log.info("Cadastrando historico {}", historicoMoto.getId());
        return historicoMotoRepository.save(historicoMoto);
    }

    @GetMapping("{id}")
    public HistoricoMoto get(@PathVariable Long id) {
        log.info("Buscando historico {}", id);
        return get(id);
    }

    @DeleteMapping("{id}")
    @CacheEvict(value = "historicos", allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando historico {}", id);
        historicoMotoRepository.delete(getHistorico(id));
    }

    @PutMapping("{id}")
    @CacheEvict(value = "historicos", allEntries = true)
    public HistoricoMoto update(@PathVariable Long id, @RequestBody @Valid HistoricoMoto historicoMoto) {
        log.info("Atualizando historico {} {}", id, historicoMoto);
        getHistorico(id);
        historicoMoto.setId(id);
        return historicoMotoRepository.save(historicoMoto);
    }

    private HistoricoMoto getHistorico(Long id) {
        return historicoMotoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Historico não encontrado"));
    }

}

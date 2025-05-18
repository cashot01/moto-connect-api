package br.com.fiap.moto_connect_api.controller;

import br.com.fiap.moto_connect_api.model.Moto;
import br.com.fiap.moto_connect_api.model.Rfid;
import br.com.fiap.moto_connect_api.repository.RfidRepository;
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
@RequestMapping("/motos/rfid")
@Slf4j
public class RfidController {

    @Autowired
    private RfidRepository rfidRepository;

    @GetMapping
    @Cacheable("rfid")
    @Operation(description = "Listar todos os Rfid", tags = "rfid", summary = "Lista dos Rfid")
    public List<Rfid> index() {
        log.info("Buscando todos os Rfid");
        return rfidRepository.findAll();
    }

    @PostMapping
    @CacheEvict(value = "rfid", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(responses = {
            @ApiResponse(responseCode = "400", description = "Falha na validação")
    })
    public Rfid create(@RequestBody @Valid Rfid rfid) {
        log.info("Cadastrando rfid {}", rfid.getId());
        return rfidRepository.save(rfid);
    }

    @GetMapping("{id}")
    public Rfid get(@PathVariable Long id) {
        log.info("Buscando rfid {}", id);
        return getRfid(id);
    }

    @DeleteMapping("{id}")
    @CacheEvict(value = "rfid", allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando rfid {}", id);
        rfidRepository.delete(getRfid(id));
    }

    @PutMapping("{id}")
    @CacheEvict(value = "rfid", allEntries = true)
    public Rfid update(@PathVariable Long id, @RequestBody @Valid Rfid rfid) {
        log.info("Atualizando rfid {} {}", id, rfid);
        getRfid(id);
        rfid.setId(id);
        return rfidRepository.save(rfid);
    }

    private Rfid getRfid(Long id) {
        return rfidRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "RFID não encontrado"));
    }
}

package br.com.fiap.moto_connect_api.controller;

import java.util.List;

import br.com.fiap.moto_connect_api.model.*;
import br.com.fiap.moto_connect_api.repository.HistoricoMotoRepository;
import br.com.fiap.moto_connect_api.repository.RfidRepository;
import br.com.fiap.moto_connect_api.specification.MotoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.moto_connect_api.repository.MotoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/*@RestController
@RequestMapping("/motos")
@Slf4j
public class MotoController {
    public record MotoFilter(String placa, TipoModelo modelo, TipoModelo status) {}

    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private RfidRepository rfidRepository;

    @Autowired
    private HistoricoMotoRepository historicoRepository;

    @GetMapping
    public Page<Moto> index(MotoFilter filter,
                            @PageableDefault(size = 10, sort = "dataCadastro", direction = Sort.Direction.DESC) Pageable pageable) {
        var specification = MotoSpecification.withFilters(filter);
        return repository.findAll(specification, pageable);
    }

    @GetMapping
    @Cacheable("motos")
    @Operation(description = "Listar todas as motos", tags = "motos", summary = "Lista das Motos")
    public List<Moto> index() {
        log.info("Buscando todas as MOTOS");
        return motoRepository.findAll();
    }

    @PostMapping
    @CacheEvict(value = "motos", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(responses = {
            @ApiResponse(responseCode = "400", description = "Falha na validação")
    })
    public Moto create(@RequestBody @Valid Moto moto) {
        log.info("Cadastrando moto {}", moto.getModelo());
        return motoRepository.save(moto);
    }

    @GetMapping("{id}")
    public Moto get(@PathVariable Long id) {
        log.info("Buscando moto {}", id);
        return getMoto(id);
    }

    @DeleteMapping("{id}")
    @CacheEvict(value = "motos", allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando moto {}", id);
        motoRepository.delete(getMoto(id));
    }

    @PutMapping("{id}")
    @CacheEvict(value = "motos", allEntries = true)
    public Moto update(@PathVariable Long id, @RequestBody @Valid Moto moto) {
        log.info("Atualizando moto {} {}", id, moto);
        getMoto(id); // valida existência
        moto.setId(id);
        return motoRepository.save(moto);
    }

    private Moto getMoto(Long id) {
        return motoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Moto não encontrada"));
    }

 */
@RestController
@RequestMapping("/motos")
@Slf4j
public class MotoController {

    public record MotoFilter(String placa, TipoModelo modelo, TipoStatus status) {}

    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private RfidRepository rfidRepository;

    @Autowired
    private HistoricoMotoRepository historicoRepository;

    @GetMapping
    @Cacheable("motos")
    @Operation(description = "Listar todas as motos com filtros e paginação", tags = "motos", summary = "Lista das Motos")
    public Page<Moto> index(MotoFilter filter,
                            @PageableDefault(size = 10, sort = "dataCadastro", direction = Sort.Direction.DESC) Pageable pageable) {
        var specification = MotoSpecification.withFilters(filter);
        log.info("Buscando motos com filtros: {}", filter);
        return motoRepository.findAll(specification, pageable);
    }

    @PostMapping
    @CacheEvict(value = "motos", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(responses = {
            @ApiResponse(responseCode = "400", description = "Falha na validação")
    })
    public Moto create(@RequestBody @Valid Moto moto) {
        log.info("Cadastrando moto {}", moto.getModelo());
        return motoRepository.save(moto);
    }

    @GetMapping("{id}")
    public Moto get(@PathVariable Long id) {
        log.info("Buscando moto {}", id);
        return getMoto(id);
    }

    @DeleteMapping("{id}")
    @CacheEvict(value = "motos", allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando moto {}", id);
        motoRepository.delete(getMoto(id));
    }

    @PutMapping("{id}")
    @CacheEvict(value = "motos", allEntries = true)
    public Moto update(@PathVariable Long id, @RequestBody @Valid Moto moto) {
        log.info("Atualizando moto {} {}", id, moto);
        getMoto(id); // valida existência
        moto.setId(id);
        return motoRepository.save(moto);
    }

    private Moto getMoto(Long id) {
        return motoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Moto não encontrada"));
    }
}




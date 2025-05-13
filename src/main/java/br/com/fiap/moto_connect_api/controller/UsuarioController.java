package br.com.fiap.moto_connect_api.controller;


import br.com.fiap.moto_connect_api.model.Moto;
import br.com.fiap.moto_connect_api.model.Usuario;

import br.com.fiap.moto_connect_api.repository.UsuarioRepository;
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
@RequestMapping("/usuarios")
@Slf4j
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;

    @GetMapping
    @Cacheable("usuarios")
    @Operation(description = "Listar todos usuarios", tags = "usuarios", summary = "Lista dos usuarios")
    public List<Usuario> index() {
        log.info("Buscando todos USUARIOS");
        return repository.findAll();
    }

    @PostMapping
    @CacheEvict(value = "usuarios", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(responses = {
            @ApiResponse(responseCode = "400", description = "Falha na validação")
    })
    public Usuario create(@RequestBody @Valid Usuario usuario) {
        log.info("Cadastrando usuario " + usuario.getNome());
        return repository.save(usuario);
    }

    @GetMapping("{id}")
    public Usuario get(@PathVariable Long id) {
        log.info("Buscando usuario " + id);
        return getUsuario(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Deletando usuario " + id);
        repository.delete(getUsuario(id));
    }

    @PutMapping("{id}")
    public Usuario update(@PathVariable Long id, @RequestBody @Valid Usuario usuario) {
        log.info("Atualizando usuario " + id + " " + usuario);

        getUsuario(id);
        usuario.setId(id);
        return repository.save(usuario);
    }

    private Usuario getUsuario(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "USUARIO NÃO ENCONTRADO"));
    }
}

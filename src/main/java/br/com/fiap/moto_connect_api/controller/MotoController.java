package br.com.fiap.moto_connect_api.controller;

import java.util.List;

import br.com.fiap.moto_connect_api.model.HistoricoMoto;
import br.com.fiap.moto_connect_api.model.Rfid;
import br.com.fiap.moto_connect_api.repository.HistoricoMotoRepository;
import br.com.fiap.moto_connect_api.repository.RfidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

import br.com.fiap.moto_connect_api.model.Moto;
import br.com.fiap.moto_connect_api.repository.MotoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/*@RestController
@RequestMapping("/motos")
@Slf4j
public class MotoController {

    @Autowired
    private MotoRepository repository;

    @Autowired
    private RfidRepository rfidRepository;

    @Autowired
    private HistoricoMotoRepository repositoryHistorico;

    @GetMapping
    @Cacheable("motos")
    @Operation(description = "Listar todas as motos", tags = "motos", summary = "Lista das Motos")
    public List<Moto> index() {
        log.info("Buscando todas as MOTOS");
        return repository.findAll();
    }

    @PostMapping
    @CacheEvict(value = "motos", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(responses = {
            @ApiResponse(responseCode = "400", description = "Falha na validação")
    })
    public Moto create(@RequestBody @Valid Moto moto) {
        log.info("Cadastrando moto " + moto.getModelo());
        return repository.save(moto);
    }

    @GetMapping("{id}")
    public Moto get(@PathVariable Long id) {
        log.info("Buscando moto " + id);
        return getMoto(id);
    }

    @DeleteMapping("{id}")
    @CacheEvict(value = "motos", allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando moto " + id);
        repository.delete(getMoto(id));
    }

    @PutMapping("{id}")
    @CacheEvict(value = "motos", allEntries = true)
    public Moto update(@PathVariable Long id, @RequestBody @Valid Moto moto) {
        log.info("Atualizando moto " + id + " " + moto);

        getMoto(id);
        moto.setId(id);
        return repository.save(moto);
    }

    // ====== NOVOS ENDPOINTS PARA RFID ======
    @GetMapping("{id}/rfid")
    @Operation(summary = "Obter RFID associado a uma moto")
    public Rfid getRfidMoto(@PathVariable Long id) {
        Moto moto = getMoto(id);
        if (moto.getRfid() == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Nenhum RFID associado a esta moto");
        }
        return moto.getRfid();
    }

    @PostMapping("{id}/rfid")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Adicionar/Atualizar RFID para uma moto")
    public Moto adicionarRfid(@PathVariable Long id, @RequestBody @Valid Rfid rfid) {
        Moto moto = getMoto(id);

        // Se já existir um RFID, remove a associação anterior
        if (moto.getRfid() != null) {
            Rfid rfidExistente = moto.getRfid();
            rfidExistente.setMoto(null);
            rfidRepository.save(rfidExistente);
        }

        rfid.setMoto(moto);
        moto.setRfid(rfid);
        return repository.save(moto);
    }

    @DeleteMapping("{id}/rfid")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remover RFID de uma moto")
    public void removerRfid(@PathVariable Long id) {
        Moto moto = getMoto(id);
        if (moto.getRfid() == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Nenhum RFID associado a esta moto");
        }

        Rfid rfid = moto.getRfid();
        moto.setRfid(null);
        rfid.setMoto(null);
        repository.save(moto);
        rfidRepository.delete(rfid);
    }

    // ====== NOVOS ENDPOINTS PARA HISTÓRICO ======
    @GetMapping("{id}/historicos")
    @Operation(summary = "Listar histórico de uma moto")
    public List<HistoricoMoto> listarHistoricos(@PathVariable Long id) {
        Moto moto = getMoto(id);
        return moto.getHistoricos();
    }

    @PostMapping("{id}/historicos")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Adicionar item ao histórico da moto")
    public HistoricoMoto adicionarHistorico(
            @PathVariable Long id,
            @RequestBody @Valid HistoricoMoto historico) {

        Moto moto = getMoto(id);
        historico.setMoto(moto);
        HistoricoMoto savedHistorico = historicoRepository.save(historico);
        moto.getHistoricos().add(savedHistorico);
        repository.save(moto);

        return savedHistorico;
    }

    @DeleteMapping("{id}/historicos/{historicoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remover item do histórico da moto")
    public void removerHistorico(
            @PathVariable Long id,
            @PathVariable Long historicoId) {

        Moto moto = getMoto(id);
        HistoricoMoto historico = historicoRepository.findById(historicoId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Item de histórico não encontrado"));

        if (!historico.getMoto().getId().equals(id)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Este histórico não pertence à moto especificada");
        }

        moto.getHistoricos().remove(historico);
        historicoRepository.delete(historico);
        repository.save(moto);
    }

    private Moto getMoto(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Moto não encontrada"));
    }
} */
@RestController
@RequestMapping("/motos")
@Slf4j
public class MotoController {

    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private RfidRepository rfidRepository;

    @Autowired
    private HistoricoMotoRepository historicoRepository;

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

    // ====== RFID ======

    @GetMapping("{id}/rfid")
    @Operation(summary = "Obter RFID associado a uma moto")
    public Rfid getRfidMoto(@PathVariable Long id) {
        Moto moto = getMoto(id);
        if (moto.getRfid() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum RFID associado a esta moto");
        }
        return moto.getRfid();
    }

    @PostMapping("{id}/rfid")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Adicionar/Atualizar RFID para uma moto")
    public Moto adicionarRfid(@PathVariable Long id, @RequestBody @Valid Rfid rfid) {
        Moto moto = getMoto(id);

        if (moto.getRfid() != null) {
            Rfid antigo = moto.getRfid();
            antigo.setMoto(null);
            rfidRepository.save(antigo);
        }

        rfid.setMoto(moto);
        moto.setRfid(rfid);
        return motoRepository.save(moto);
    }

    @DeleteMapping("{id}/rfid")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remover RFID de uma moto")
    public void removerRfid(@PathVariable Long id) {
        Moto moto = getMoto(id);
        Rfid rfid = moto.getRfid();

        if (rfid == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum RFID associado a esta moto");
        }

        moto.setRfid(null);
        rfid.setMoto(null);
        motoRepository.save(moto);
        rfidRepository.delete(rfid);
    }

    // ====== HISTÓRICO ======

    @GetMapping("{id}/historicos")
    @Operation(summary = "Listar histórico de uma moto")
    public List<HistoricoMoto> listarHistoricos(@PathVariable Long id) {
        return getMoto(id).getHistoricos();
    }

    @PostMapping("{id}/historicos")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Adicionar item ao histórico da moto")
    public HistoricoMoto adicionarHistorico(
            @PathVariable Long id,
            @RequestBody @Valid HistoricoMoto historico) {

        Moto moto = getMoto(id);
        historico.setMoto(moto);
        return historicoRepository.save(historico);
    }

    @DeleteMapping("{id}/historicos/{historicoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remover item do histórico da moto")
    public void removerHistorico(@PathVariable Long id, @PathVariable Long historicoId) {
        Moto moto = getMoto(id);
        HistoricoMoto historico = historicoRepository.findById(historicoId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Item de histórico não encontrado"));

        if (!historico.getMoto().getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este histórico não pertence à moto especificada");
        }

        historicoRepository.delete(historico);
    }

    // ====== MÉTODO AUXILIAR ======
    private Moto getMoto(Long id) {
        return motoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Moto não encontrada"));
    }
}


package br.com.fiap.moto_connect_api.repository;

import br.com.fiap.moto_connect_api.model.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {
    List<Localizacao> findByMotoIdOrderByDataHoraDesc(Long motoId);
}

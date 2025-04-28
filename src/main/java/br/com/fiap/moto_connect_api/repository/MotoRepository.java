package br.com.fiap.moto_connect_api.repository;

import br.com.fiap.moto_connect_api.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotoRepository extends JpaRepository<Moto, Long> {

}

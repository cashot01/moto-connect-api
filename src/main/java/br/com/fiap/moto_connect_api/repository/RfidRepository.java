package br.com.fiap.moto_connect_api.repository;

import br.com.fiap.moto_connect_api.model.Rfid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RfidRepository extends JpaRepository<Rfid, Long> {

}

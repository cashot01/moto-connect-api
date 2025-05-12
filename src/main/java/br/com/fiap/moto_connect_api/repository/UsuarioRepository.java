package br.com.fiap.moto_connect_api.repository;

import br.com.fiap.moto_connect_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}

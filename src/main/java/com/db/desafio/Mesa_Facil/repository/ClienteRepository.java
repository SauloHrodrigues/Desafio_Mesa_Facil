package com.db.desafio.Mesa_Facil.repository;

import com.db.desafio.Mesa_Facil.model.Cliente;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    Optional<Cliente> findByNomeIgnoreCase(String nome);
}

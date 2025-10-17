package com.db.desafio.Mesa_Facil.repository;

import com.db.desafio.Mesa_Facil.model.Cliente;
import com.db.desafio.Mesa_Facil.model.Reserva;
import com.db.desafio.Mesa_Facil.model.Restaurante;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva,Long> {
    Optional<Reserva> findByClienteAndRestauranteAndDataHora(
            Cliente cliente,
            Restaurante restaurante,
            LocalDateTime dataHora
    );
}
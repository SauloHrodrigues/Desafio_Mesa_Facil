package com.db.desafio.Mesa_Facil.repository;

import com.db.desafio.Mesa_Facil.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva,Long> {

}
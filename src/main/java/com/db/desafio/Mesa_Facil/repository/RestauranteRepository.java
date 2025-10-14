package com.db.desafio.Mesa_Facil.repository;

import com.db.desafio.Mesa_Facil.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante,Long> {
}

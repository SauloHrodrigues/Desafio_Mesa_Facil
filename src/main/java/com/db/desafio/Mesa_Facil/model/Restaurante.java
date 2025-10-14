package com.db.desafio.Mesa_Facil.model;

import com.db.desafio.Mesa_Facil.enuns.StatusMesa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "restaurantes")
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;


    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Reserva> reservas = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Restaurante that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getNome().toLowerCase(), that.getNome().toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome().toLowerCase());
    }
}

package com.db.desafio.Mesa_Facil.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime dataHora;


    @ManyToOne()
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private Cliente cliente;


    @ManyToOne()
    @JoinColumn(name = "restaurante_id")
    @JsonIgnore
    @ToString.Exclude
    private Restaurante restaurante;

    @PrePersist
    @PreUpdate
    private void ajustarPrecisao() {
        if (dataHora != null) {
            dataHora = dataHora.truncatedTo(ChronoUnit.MINUTES);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reserva reserva)) return false;
        return Objects.equals(getDataHora(), reserva.getDataHora())
                && Objects.equals(getCliente(), reserva.getCliente())
                && Objects.equals(getRestaurante(), reserva.getRestaurante());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDataHora(), getCliente(), getRestaurante());
    }
}
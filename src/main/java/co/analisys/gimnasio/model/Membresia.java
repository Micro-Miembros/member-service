package co.analisys.gimnasio.model;

import java.time.LocalDate;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Membresia {
    private LocalDate fechaInscripcion;
    private LocalDate fechaExpiracion;
    private Tipo tipo;

    public Membresia() {
        this.fechaInscripcion = LocalDate.now();
        this.fechaExpiracion = fechaInscripcion.plusMonths(1);
        this.tipo = Tipo.BASICO;
    }
}
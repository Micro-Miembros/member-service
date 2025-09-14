package co.analisys.gimnasio.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResumenEntrenamiento {
    private String miembroId;
    private int totalSesiones;
    private int totalMinutos;
    private int totalCalorias;
    private double promedioSesiones;
    
    public ResumenEntrenamiento actualizar(DatosEntrenamiento datos) {
        this.miembroId = datos.getMiembroId();
        this.totalSesiones++;
        this.totalMinutos += datos.getDuracionMinutos();
        this.totalCalorias += datos.getCaloriasQuemadas();
        this.promedioSesiones = (double) totalMinutos / totalSesiones;
        return this;
    }
}
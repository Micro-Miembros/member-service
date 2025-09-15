package co.analisys.gimnasio.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PagoMessage {
    private Long miembroId;
    private String nombreMiembro;
    private String emailMiembro;
    private BigDecimal montoMembresia = new BigDecimal("99.99");
    private String tipoMembresia;
    private LocalDateTime fechaRegistro;
    private Boolean simularFalloPago = false;

    public PagoMessage() {}

    public PagoMessage(Long miembroId, String nombreMiembro, String emailMiembro, 
                      String tipoMembresia, Boolean simularFalloPago) {
        this.miembroId = miembroId;
        this.nombreMiembro = nombreMiembro;
        this.emailMiembro = emailMiembro;
        this.montoMembresia = new BigDecimal("99.99");
        this.tipoMembresia = tipoMembresia;
        this.simularFalloPago = simularFalloPago != null ? simularFalloPago : false;
        this.fechaRegistro = LocalDateTime.now();
    }

    public Long getMiembroId() {
        return miembroId;
    }

    public void setMiembroId(Long miembroId) {
        this.miembroId = miembroId;
    }

    public String getNombreMiembro() {
        return nombreMiembro;
    }

    public void setNombreMiembro(String nombreMiembro) {
        this.nombreMiembro = nombreMiembro;
    }

    public String getEmailMiembro() {
        return emailMiembro;
    }

    public void setEmailMiembro(String emailMiembro) {
        this.emailMiembro = emailMiembro;
    }

    public BigDecimal getMontoMembresia() {
        return montoMembresia;
    }

    public void setMontoMembresia(BigDecimal montoMembresia) {
        this.montoMembresia = montoMembresia;
    }

    public String getTipoMembresia() {
        return tipoMembresia;
    }

    public void setTipoMembresia(String tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Boolean getSimularFalloPago() {
        return simularFalloPago != null ? simularFalloPago : false;
    }

    public void setSimularFalloPago(Boolean simularFalloPago) {
        this.simularFalloPago = simularFalloPago;
    }

    @Override
    public String toString() {
        return "PagoMessage{" +
                "miembroId=" + miembroId +
                ", nombreMiembro='" + nombreMiembro + '\'' +
                ", emailMiembro='" + emailMiembro + '\'' +
                ", montoMembresia=" + montoMembresia +
                ", tipoMembresia='" + tipoMembresia + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", simularFalloPago=" + simularFalloPago +
                '}';
    }
}
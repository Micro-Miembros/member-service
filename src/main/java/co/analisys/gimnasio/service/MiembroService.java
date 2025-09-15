package co.analisys.gimnasio.service;

import co.analisys.gimnasio.dto.PagoMessage;
import co.analisys.gimnasio.exception.MiembroNoEncontrado;
import co.analisys.gimnasio.model.Miembro;
import co.analisys.gimnasio.repository.MiembroRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MiembroService {
    @Autowired
    private MiembroRepository miembroRepository;
    
    @Autowired
    private NotificationService notificationService;
    
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Miembro registrarMiembro(Miembro miembro) {
        Miembro miembroRegistrado = miembroRepository.save(miembro);
        
        notificationService.enviarNotificacionRegistroMiembro(miembroRegistrado);
        
        enviarSolicitudPagoMembresia(miembroRegistrado, miembro.getSimularFalloPago());
        
        return miembroRegistrado;
    }
    
    public boolean membresiaActiva(Long id) {
        
        if (!miembroRepository.existsById(id)) {
            throw new MiembroNoEncontrado(id);
        }

        Miembro miembro = miembroRepository.findById(id).get();
        
        return miembro != null && miembro.getMembresia().getFechaExpiracion().isAfter(LocalDate.now());
    }

    public List<Miembro> obtenerTodosMiembros() {
        return miembroRepository.findAll();
    }
    
    private void enviarSolicitudPagoMembresia(Miembro miembro, Boolean simularFallo) {
        try {
            String tipoMembresia = miembro.getMembresia() != null ? 
                miembro.getMembresia().getTipo().toString() : "MENSUAL";
                
            PagoMessage pagoMessage = new PagoMessage(
                miembro.getId(),
                miembro.getNombre(),
                miembro.getEmail(),
                tipoMembresia,
                simularFallo
            );
            
            rabbitTemplate.convertAndSend(
                "pagos-exchange",
                "pago.procesar",
                pagoMessage
            );
            
            String modo = simularFallo ? " (SIMULANDO FALLO)" : "";
            System.out.println("Solicitud de pago enviada para miembro: " + miembro.getNombre() + modo);
            
        } catch (Exception e) {
            System.err.println("Error enviando solicitud de pago para miembro: " + miembro.getNombre());
            e.printStackTrace();
        }
    }
}
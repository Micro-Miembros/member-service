package co.analisys.gimnasio.service;

import co.analisys.gimnasio.exception.MiembroNoEncontrado;
import co.analisys.gimnasio.model.Miembro;
import co.analisys.gimnasio.repository.MiembroRepository;
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

    public Miembro registrarMiembro(Miembro miembro) {
        Miembro miembroRegistrado = miembroRepository.save(miembro);
        
        notificationService.enviarNotificacionRegistroMiembro(miembroRegistrado);
        
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
}
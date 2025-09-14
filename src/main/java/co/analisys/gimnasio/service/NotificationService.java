package co.analisys.gimnasio.service;

import co.analisys.gimnasio.config.RabbitMQConfig;
import co.analisys.gimnasio.dto.MiembroRegistroMessage;
import co.analisys.gimnasio.model.Miembro;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    public void enviarNotificacionRegistroMiembro(Miembro miembro) {
        try {
            MiembroRegistroMessage mensaje = new MiembroRegistroMessage(
                miembro.getNombre(),
                miembro.getEmail()
            );
            
            rabbitTemplate.convertAndSend(
                RabbitMQConfig.MIEMBRO_EXCHANGE,
                RabbitMQConfig.MIEMBRO_ROUTING_KEY,
                mensaje
            );
            
            System.out.println("Mensaje de registro enviado a RabbitMQ para miembro: " + miembro.getNombre());
        } catch (Exception e) {
            System.err.println("Error enviando mensaje a RabbitMQ para miembro: " + miembro.getNombre());
            e.printStackTrace();
        }
    }
}

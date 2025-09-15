package co.analisys.gimnasio.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    
    public static final String MIEMBRO_QUEUE = "miembro.registro.queue";
    public static final String MIEMBRO_EXCHANGE = "miembro.exchange";
    public static final String MIEMBRO_ROUTING_KEY = "miembro.registro";
    
    public static final String PAGOS_EXCHANGE = "pagos-exchange";
    public static final String PAGOS_ROUTING_KEY = "pago.procesar";
    
    @Bean
    public Queue miembroRegistroQueue() {
        return new Queue(MIEMBRO_QUEUE, true);
    }

    @Bean
    public TopicExchange miembroExchange() {
        return new TopicExchange(MIEMBRO_EXCHANGE);
    }

    @Bean
    public Binding miembroBinding(Queue miembroRegistroQueue, TopicExchange miembroExchange) {
        return BindingBuilder.bind(miembroRegistroQueue).to(miembroExchange).with(MIEMBRO_ROUTING_KEY);
    }

    @Bean
    public DirectExchange pagosExchange() {
        return new DirectExchange(PAGOS_EXCHANGE);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}

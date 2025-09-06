package com.example.producer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "myQueue.bootcamp";
    public static final String EXCHANGE_NAME = "exchange.direct.bootcamp"; // Nombre del intercambio predefinido

    public static final String ROUTING_KEY = "myRoutingKey.bootcamp";

    @Bean
    Queue createQueue () {
        // TTL de 1 hora (opcional)
        return QueueBuilder.durable (QUEUE_NAME)
//                .withArgument ("x-message-ttl", 3600000)
                .build ();
    }

    @Bean
    DirectExchange exchange () {
        return ExchangeBuilder.directExchange (EXCHANGE_NAME)
                .durable (true)
                .build ();
    }

    @Bean
    Binding binding (Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind (queue)
                .to (exchange)
                .with (ROUTING_KEY);
    }

    // ESTE BEAN ESTÁ COMENTADO PARA EVITAR CONSUMO AUTOMÁTICO
    // Si lo descomentamos, consumirá automáticamente los mensajes
    /*
    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }
    */
}
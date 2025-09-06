package com.example.producer.service.impl;

import com.example.producer.config.RabbitMQConfig;
import com.example.producer.service.ProduceMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProduceMessageServiceImpl implements ProduceMessageService {

    private final RabbitTemplate rabbitTemplate;

    public String produceMessage (String message) {
        log.info("Producing message: {}", message);

        // Check message count BEFORE sending
        Long messageCountBefore = getMessageCount();
        log.info("Messages in queue BEFORE sending: {}", messageCountBefore);

        // Configurar propiedades del mensaje para persistencia
        MessageProperties properties = new MessageProperties ();
        properties.setDeliveryMode (MessageDeliveryMode.PERSISTENT); // Mensaje persistente
        properties.setContentType ("text/plain");

        Message rabbitMessage = new Message (message.getBytes (), properties);

        // Enviar mensaje
        rabbitTemplate.send (RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, rabbitMessage);

        log.info ("Persistent message sent to exchange: {} with routing key: {}", RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY);

        // Check message count immediately after sending
        Long messageCountImmediate = getMessageCount ();
        log.info ("Messages in queue IMMEDIATELY after sending: {}", messageCountImmediate);

        // Wait a moment and check again
        try {
            Thread.sleep (100); // Small delay to let RabbitMQ process
        } catch (InterruptedException e) {
            Thread.currentThread ()
                    .interrupt ();
        }

        // Check message count AFTER sending
        Long messageCountAfter = getMessageCount ();
        log.info ("Messages in queue AFTER sending: {}", messageCountAfter);

        return String.format ("Message(%s) has been produced with persistence. Queue had %d messages, now has %d messages.", message, messageCountBefore, messageCountAfter);
    }

    private Long getMessageCount () {
        return rabbitTemplate.execute (channel -> {
            try {
                return channel.messageCount (RabbitMQConfig.QUEUE_NAME);
            } catch (Exception e) {
                log.error ("Error getting message count: {}", e.getMessage ());
                return -1L;
            }
        });
    }
}
package com.epam.rd.sender;

import com.epam.rd.caclulation.Calculator;
import com.epam.rd.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.epam.rd.config.ConfigActiveMQ.*;

@Service
public class Sender {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);
    private final JmsTemplate jmsTemplate;
    private Set<Message> messages;

    @Autowired
    public Sender(JmsTemplate jmsTemplate, Set<Message> messages) {
        this.jmsTemplate = jmsTemplate;
        this.messages = new HashSet<>();
    }

    public void send(Message message) {
        messages.add(message);
        LOGGER.info("Message has been sent. Message details: {}", message);
        jmsTemplate.convertAndSend(REQUEST_CHANNEL, message);
    }

    @JmsListener(destination = RESPONSE_CHANNEL)
    public void correlation(@Payload Message message) {
        try {
            Message senderMessage = messages.stream()
                    .filter(m -> m.getCorrelationId().equals(message.getCorrelationId()))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
            if (message.getResult() != Calculator.sum(senderMessage.getFirstNumber(), senderMessage.getSecondNumber())) {
                jmsTemplate.convertAndSend(IMBALANCE_MESSAGES, message);
            } else {
                LOGGER.info("Message return with result: {}", message);
            }
        } catch (IllegalArgumentException ex) {
            jmsTemplate.convertAndSend(IMBALANCE_MESSAGES, String.format("Correlation id doesn't match. Message info: %s", message));
        }
    }

}

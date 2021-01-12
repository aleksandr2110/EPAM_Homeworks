package com.epam.rd.responder;

import com.epam.rd.caclulation.Calculator;
import com.epam.rd.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import static com.epam.rd.Homework14Application.MESSAGE_COUNT;
import static com.epam.rd.config.ConfigActiveMQ.REQUEST_CHANNEL;
import static com.epam.rd.config.ConfigActiveMQ.RESPONSE_CHANNEL;

@Component
public class Responder {

    private static final Logger LOGGER = LoggerFactory.getLogger(Responder.class);
    private final JmsTemplate jmsTemplate;
    private Set<Message> messages;

    @Autowired
    public Responder(JmsTemplate jmsTemplate, Set<Message> messages) {
        this.jmsTemplate = jmsTemplate;
        this.messages = new HashSet<>();
    }

    @JmsListener(destination = REQUEST_CHANNEL)
    public void saveMessage(@Payload Message message) {
        messages.add(message);
        if (messages.size() == MESSAGE_COUNT) {
            LOGGER.info("Received {} messages", MESSAGE_COUNT);
            process();
        }
    }

    public void process() {
        int result;
        for (Message message : messages) {
            result = Calculator.sum(message.getFirstNumber(), message.getSecondNumber());
            message.setResult(result);
            jmsTemplate.convertAndSend(RESPONSE_CHANNEL, message);
        }
    }

}

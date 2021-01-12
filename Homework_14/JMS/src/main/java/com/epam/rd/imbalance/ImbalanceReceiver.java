package com.epam.rd.imbalance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.epam.rd.config.ConfigActiveMQ.IMBALANCE_MESSAGES;

@Component
public class ImbalanceReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImbalanceReceiver.class);

    @JmsListener(destination = IMBALANCE_MESSAGES)
    public void receive(@Payload String message) {
        LOGGER.warn("Invalid result. Message info: {}", message);
    }

}

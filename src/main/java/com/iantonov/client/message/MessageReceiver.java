package com.iantonov.client.message;

import com.iantonov.client.dao.ClientInformationDao;
import com.iantonov.client.domain.ClientInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    private final ClientInformationDao dao;
    private final Logger log = LoggerFactory.getLogger(MessageReceiver.class);

    public MessageReceiver(ClientInformationDao dao) {
        this.dao = dao;
    }

    @JmsListener(destination = "bank", containerFactory = "myFactory")
    public void receiveMessage(ClientInformation information) {
        dao.save(information);
        log.info("Received: " + information);
    }
}

package com.iantonov.client.service;

import com.iantonov.client.domain.ClientInformation;
import com.iantonov.client.domain.Success;
import com.iantonov.client.dto.ClientInformationResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageSenderServiceImpl implements MessageSenderService {

    private final Logger log = LoggerFactory.getLogger(MessageSenderServiceImpl.class);
    private final JmsTemplate jmsTemplate;

    public MessageSenderServiceImpl(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public ClientInformationResponseDto sendMessage(ClientInformation info){
        log.info("Sending the message.");
        jmsTemplate
                .convertAndSend("bank",
                        new ClientInformation(info.getServiceName(), info.getManagerLogin()
                                , info.getServiceTimeStart(), info.getServiceTimeEnd()));
        return new ClientInformationResponseDto(Success.ok, "success");
    }
}

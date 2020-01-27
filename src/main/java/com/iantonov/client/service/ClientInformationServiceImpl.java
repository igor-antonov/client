package com.iantonov.client.service;

import com.iantonov.client.dao.ClientInformationDao;
import com.iantonov.client.domain.ClientInformation;
import com.iantonov.client.domain.Success;
import com.iantonov.client.dto.ClientInformationResponseDto;
import com.iantonov.client.dto.ManagerResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ClientInformationServiceImpl implements ClientInformationService {

    private final Logger log = LoggerFactory.getLogger(ClientInformationServiceImpl.class);
    private final MessageSenderService senderService;
    private final ClientInformationDao informationDao;

    public ClientInformationServiceImpl(MessageSenderService senderService, ClientInformationDao informationDao) {
        this.senderService = senderService;
        this.informationDao = informationDao;
    }

    public ClientInformationResponseDto save(ClientInformation info) {

        try {
            return senderService.sendMessage(info);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ClientInformationResponseDto(Success.error, "failure");
        }
    }

    public ManagerResponseDto findAll() {
        return informationDao.findAll();
    }
}

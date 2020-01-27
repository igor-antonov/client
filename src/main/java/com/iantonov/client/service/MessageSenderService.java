package com.iantonov.client.service;

import com.iantonov.client.domain.ClientInformation;
import com.iantonov.client.dto.ClientInformationResponseDto;

public interface MessageSenderService {
    ClientInformationResponseDto sendMessage(ClientInformation info);
}

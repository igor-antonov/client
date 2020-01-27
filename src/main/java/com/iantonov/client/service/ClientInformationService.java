package com.iantonov.client.service;

import com.iantonov.client.domain.ClientInformation;
import com.iantonov.client.dto.ClientInformationResponseDto;
import com.iantonov.client.dto.ManagerResponseDto;

public interface ClientInformationService {
    ClientInformationResponseDto save(ClientInformation info);
    ManagerResponseDto findAll();
}

package com.iantonov.client.controller;

import com.iantonov.client.domain.ClientInformation;
import com.iantonov.client.dto.ClientInformationResponseDto;
import com.iantonov.client.dto.ManagerResponseDto;
import com.iantonov.client.service.ClientInformationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "client", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ClientInformationController {

    private final ClientInformationService service;

    public ClientInformationController(ClientInformationService service) {
        this.service = service;
    }

    @PostMapping
    public ClientInformationResponseDto save(@RequestBody ClientInformation info) {
        return service.save(info);
    }


    @GetMapping
    public ManagerResponseDto findAll(HttpServletResponse response){
        return service.findAll();
    }
}

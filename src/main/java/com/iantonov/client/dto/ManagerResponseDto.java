package com.iantonov.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.iantonov.client.domain.ClientInformation;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@JsonSerialize(using = ManagerResponseSerializer.class)
public class ManagerResponseDto {

    private Map<String, List<ClientInformation>> listMap = new HashMap<>();
}

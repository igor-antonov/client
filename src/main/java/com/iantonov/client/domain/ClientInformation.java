package com.iantonov.client.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "serviceName",
        "managerLogin",
        "serviceTimeStart",
        "serviceTimeEnd"
})
public class ClientInformation {

    @JsonProperty("serviceName")
    private String serviceName;
    @JsonProperty("managerLogin")
    private String managerLogin;

    //для генерации класса из схемы использовал http://www.jsonschema2pojo.org/
    //но я бы предпочел дату java.time.LocalDate
    @JsonProperty("serviceTimeStart")
    private Date serviceTimeStart;

    //в схеме по идее тоже должен быть тип Date, но оставил как есть..
    @JsonProperty("serviceTimeEnd")
    private String serviceTimeEnd;
}
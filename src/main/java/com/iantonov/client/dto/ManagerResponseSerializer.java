package com.iantonov.client.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ManagerResponseSerializer extends StdSerializer<ManagerResponseDto> {

    private final Logger log = LoggerFactory.getLogger(ManagerResponseSerializer.class);
    public ManagerResponseSerializer(){
        this(null);
    }

    public ManagerResponseSerializer(Class<ManagerResponseDto> t){
        super(t);
    }

    @Override
    public void serialize(ManagerResponseDto resp, JsonGenerator jGen, SerializerProvider provider) throws IOException {

        if (resp == null) {
            provider.defaultSerializeNull(jGen);
        }
        else {
            jGen.writeStartObject();
            jGen.writeFieldName("managerList");
            jGen.writeStartArray();

            resp.getListMap().forEach((k, v) -> {
                try {
                    jGen.writeStartObject();
                    jGen.writeStringField("login", k);

                    jGen.writeFieldName("serviceList");
                    jGen.writeStartArray();

                    v.forEach(client -> {
                        try {
                            jGen.writeStartObject();
                            jGen.writeStringField("serviceName", client.getServiceName());
                            jGen.writeStringField("serviceTimeStart", client.getServiceTimeStart().toString());
                            jGen.writeStringField("serviceTimeEnd", client.getServiceTimeEnd());
                            jGen.writeEndObject();
                        } catch (IOException e) {
                            log.error(e.getMessage());
                        }
                    });

                    jGen.writeEndArray();
                    jGen.writeEndObject();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            });

            jGen.writeEndArray();
            jGen.writeEndObject();
        }
    }
}

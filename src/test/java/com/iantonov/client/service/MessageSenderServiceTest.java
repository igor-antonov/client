package com.iantonov.client.service;

import com.iantonov.client.domain.ClientInformation;
import com.iantonov.client.domain.Success;
import com.iantonov.client.dto.ClientInformationResponseDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageSenderServiceTest {

    @Autowired
    MessageSenderService senderService;
    private ClientInformation info;

    @Before
    public void prepare() {
        info =
                new ClientInformation("sale", "anna",
                        new Date(0, 0, 0, 0, 0, 0), "december");
        ClientInformationResponseDto responseDto = new ClientInformationResponseDto(Success.ok, "success");
    }

    @Test
    public void send(){
        Assert.assertEquals(senderService.sendMessage(info).getSuccess(), Success.ok);
    }
}

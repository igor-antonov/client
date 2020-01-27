package com.iantonov.client.service;

import com.iantonov.client.dao.ClientInformationDao;
import com.iantonov.client.domain.ClientInformation;
import com.iantonov.client.domain.Success;
import com.iantonov.client.dto.ManagerResponseDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientInformationServiceTest {
    @MockBean
    private ClientInformationDao dao;
    @Autowired
    private ClientInformationService informationService;
    private ClientInformation info;
    private ManagerResponseDto managerResp;

    @Before
    public void prepare() {
        info =
                new ClientInformation("sale", "anna",
                        new Date(0, 0, 0, 0, 0, 0), "december");
        List<ClientInformation> informationList = new ArrayList<>();
        informationList.add(info);
        managerResp = new ManagerResponseDto();
        managerResp.getListMap().put(info.getManagerLogin(), informationList);
    }

    @Test
    public void findAll() {
        given(dao.findAll()).willReturn(managerResp);
        Assert.assertTrue(informationService.findAll().getListMap().containsKey(info.getManagerLogin()));
    }

    @Test
    public void save(){
        Assert.assertEquals(informationService.save(info).getSuccess(), Success.ok);
    }
}

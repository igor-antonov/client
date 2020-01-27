package com.iantonov.client.dao;

import com.iantonov.client.domain.ClientInformation;
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
public class ClientInformationDaoTest {

    @Autowired
    private ClientInformationDao dao;
    private ClientInformation info;

    @Before
    public void prepare(){
        info =
                new ClientInformation("sale", "anna",
                        new Date(0, 0, 0, 0, 0, 0), "december");
        dao.save(info);
    }

    @Test
    public void save(){
        info =
                new ClientInformation("product", "login",
                        new Date(0, 0, 0, 0, 0, 0), "december");
        Assert.assertTrue(dao.save(info));
    }

    @Test
    public void findAll(){
        Assert.assertTrue(dao.findAll().getListMap().containsKey("anna"));
    }
}

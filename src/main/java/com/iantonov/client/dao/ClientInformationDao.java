package com.iantonov.client.dao;

import com.iantonov.client.domain.ClientInformation;
import com.iantonov.client.dto.ManagerResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientInformationDao {

    private final Logger log = LoggerFactory.getLogger(ClientInformationDao.class);
    private final DataSource source;

    private ManagerResponseDto managerResp = new ManagerResponseDto();

    public ClientInformationDao(DataSource source) {
        this.source = source;
    }

    public boolean save(ClientInformation information){

        String sql = "insert into client_information (" +
                "SERVICE_NAME, " +
                "MANAGER_LOGIN, " +
                "SERVICE_TIME_START, " +
                "SERVICE_TIME_END" +
                ") values (?,?,?,?)";
        try(Connection conn = source.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)){

            statement.setString(1, information.getServiceName());
            statement.setString(2, information.getManagerLogin());
            statement.setDate(3,
                    new Date(information.getServiceTimeStart().getTime()));
            statement.setString(4, information.getServiceTimeEnd());
            return statement.executeUpdate() == 1;
        }
        catch (SQLException e){
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public ManagerResponseDto findAll(){

        try(Connection conn = source.getConnection();
            Statement statement = conn.createStatement()){

            String sql = "select * from client_information";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                String login = rs.getString("MANAGER_LOGIN");
                ClientInformation info = new ClientInformation();
                info.setServiceName(rs.getString("SERVICE_NAME"));
                info.setServiceTimeStart(rs.getDate("SERVICE_TIME_START"));
                info.setServiceTimeEnd(rs.getString("SERVICE_TIME_END"));
                if (managerResp.getListMap().containsKey(login)){
                    managerResp.getListMap().get(login).add(info);
                }
                else {
                    List<ClientInformation> informationList = new ArrayList<>();
                    informationList.add(info);
                    managerResp.getListMap().put(login, informationList);
                }
            }
            return managerResp;
        }
        catch (SQLException e){
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

package com.hierarquia.cliente.mapper;

import com.hierarquia.cliente.ClienteApplication;
import com.hierarquia.cliente.model.entity.ClientEntity;
import com.hierarquia.cliente.model.response.ClientResponse;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.stream.Collectors;

public class ClientMapper {

    public static ClientResponse mapResponse(ClientEntity entity){

        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setId(entity.getId());
        clientResponse.setPasswordClient(entity.getPasswordClient());
        clientResponse.setUserClient(entity.getUserClient());
        clientResponse.setPasswordValue(entity.getPasswordValue());
        clientResponse.setPasswordStatus(entity.getPasswordStatus());

        return clientResponse;
    }


    public static List<ClientResponse> mapResponseList(List<ClientEntity> entities){

        return entities.stream()
                .map(ClientMapper::mapResponse)
                .collect(Collectors.toList());
    }

    public static ClientEntity percent(ClientEntity entity, String percent, String status){
        entity.setPasswordValue(percent);
        entity.setPasswordStatus(status);
        return entity;
    }

}

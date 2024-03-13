package com.hierarquia.cliente.controller;

import ch.qos.logback.core.net.server.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hierarquia.cliente.model.entity.ClientEntity;
import com.hierarquia.cliente.model.request.ClientRequest;
import com.hierarquia.cliente.model.response.ClientResponse;
import com.hierarquia.cliente.repository.ClientRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

    @InjectMocks
    private ClientController controller;

    @Mock
    private ClientRepository repository;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    public void clientListOk() throws Exception{
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setUserClient("asdas");
        clientResponse.setPasswordClient("asdas");

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setPasswordClient("213123");
        clientEntity.setUserClient("234234");

        List<ClientEntity> entities = new ArrayList<>();
        entities.add(clientEntity);

        when(repository.findAll()).thenReturn(entities);

        mockMvc.perform(get("/Client")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userClient", is(entities.get(0).getUserClient())));
    }

    @Test
    public void clientListNot() throws Exception{

        mockMvc.perform(get("/Client1")).andExpect(status().isNotFound());
    }


    @Test
    public void clientSaveOk() throws Exception{

        ClientRequest request = new ClientRequest();
        request.setPasswordClient("asdasd");
        request.setUserClient("asdasd");

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setPasswordClient("213123");
        clientEntity.setUserClient("234234");

        List<ClientEntity> entities = new ArrayList<>();
        entities.add(clientEntity);

        when(repository.save(any())).thenReturn(clientEntity);

        mockMvc.perform(post("/Client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userClient", is(clientEntity.getUserClient())));
    }

}

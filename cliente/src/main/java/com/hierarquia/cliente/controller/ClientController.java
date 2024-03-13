package com.hierarquia.cliente.controller;

import com.hierarquia.cliente.mapper.ClientMapper;
import com.hierarquia.cliente.model.entity.ClientEntity;
import com.hierarquia.cliente.model.request.ClientRequest;
import com.hierarquia.cliente.model.response.ClientResponse;
import com.hierarquia.cliente.repository.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/Client")
@CrossOrigin(origins = "http://localhost:8081")
public class ClientController {

    @Autowired
    private ClientRepository repository;
    @GetMapping("/Client")
    public String getExample() {
        return "Hello from Spring Boot!";
    }
    //@GetMapping
   ///public ResponseEntity<List<ClientResponse>> findAll() {

        //return ResponseEntity.ok(ClientMapper.mapResponseList(repository.findAll()));
    //}

    @GetMapping
    public ResponseEntity<List<ClientResponse>> findAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "https://localhost:8081");
        headers.add("Access-Control-Allow-Headers", "content-type, x-teste");
        headers.add("Access-Control-Allow-Methods", "PUT, PATCH, DELETE, POST, GET");

        return ResponseEntity.ok()
                .headers(headers)
                .body(ClientMapper.mapResponseList(repository.findAll()));
    }

    @PostMapping
    public ResponseEntity<ClientResponse> save(@RequestBody ClientEntity entity) {
        String password = entity.getPasswordClient();
        int passwordLength = password.length();
        String percent = "15%";
        String label = "Ruim";
        if (passwordLength >= 2 && passwordLength < 4) {
            percent = "30%";
            label = "Médio";
        } else if (passwordLength >= 4 && passwordLength < 6) {
            percent = "45%";
            label = "Bom";
        } else if (passwordLength >= 6 && passwordLength < 8) {
            percent = "60%";
            label = "Ótimo";
        } else if (passwordLength >= 8 && passwordLength < 10) {
            percent = "75%";
            label = "Excelente";
        } else if (passwordLength >= 10) {
            percent = "90%";
            label = "Superior";
        }
        entity = ClientMapper.percent(entity, percent, label);
        ClientResponse response = ClientMapper.mapResponse(repository.save(entity));

        return ResponseEntity.ok(response);
    }
}

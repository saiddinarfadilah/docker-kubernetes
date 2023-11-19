package com.mysql.jdbc.databases;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MainService {

    private final DatabaseService databaseService;
    private final ObjectMapper mapper;

    public MainService(DatabaseService databaseService, ObjectMapper mapper) {
        this.databaseService = databaseService;
        this.mapper = mapper;
    }

    public ResponseEntity<Object> general(String request) {
        DatabaseModel response = new DatabaseModel();
        Map<String, Object> requestMap;
        try {
            requestMap = mapper.readValue(request, new TypeReference<Map<String, Object>>() {});
        } catch (JsonProcessingException e) {
            log.warn(e.getMessage());
            response.setIsSuccess(false);
            response.setResult(e.getMessage());
            response.setParams(List.of());
            return ResponseEntity.internalServerError().body(response);
        }

        log.info(requestMap.toString());
        DatabaseModel result = databaseService.execute(requestMap);
        log.info(result.toString());
        if (result.getIsSuccess().equals(true)) {
            response.setIsSuccess(true);
            response.setResult(result.getResult());
            response.setParams(result.getParams());
            return ResponseEntity.ok(response);
        }
        response.setIsSuccess(false);
        response.setResult(result.getResult());
        response.setParams(result.getParams());
        return ResponseEntity.internalServerError().body(response);
    }
}

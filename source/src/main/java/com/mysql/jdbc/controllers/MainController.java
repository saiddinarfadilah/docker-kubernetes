package com.mysql.jdbc.controllers;

import com.mysql.jdbc.databases.MainService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class MainController {

    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping(value = {"/**"} , method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Object> route(@RequestBody(required = false) String request) {
        return mainService.general(request);
    }
}

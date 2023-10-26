package com.github.valentina810.weekplannerformarusia.service;

import com.github.valentina810.weekplannerformarusia.model.MarusiaRequest;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class WeekPlannerServiceImpl implements WeekPlannerService {

    private MarusiaRequest marusiaRequest;

    @Override
    public ResponseEntity<?> getResponse(Object object) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        return new ResponseEntity<>(new Gson().toJson(marusiaRequest.getRequest(object)),
                headers, HttpStatus.OK);
    }
}
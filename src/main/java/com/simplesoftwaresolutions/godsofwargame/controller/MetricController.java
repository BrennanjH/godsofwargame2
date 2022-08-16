package com.simplesoftwaresolutions.godsofwargame.controller;

import com.simplesoftwaresolutions.godsofwargame.controller.service.MetricService;
import com.simplesoftwaresolutions.godsofwargame.dto.ServerMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller is the home to endpoints that provide details related to server connections and state
 */
@RestController("/servermetric")
public class MetricController {
    @Autowired MetricService metricService;
    /**
     * @return a DTO of the servers ongoing metrics, such as player counts or gameStatus
     */
    @PostMapping
    public ResponseEntity<ServerMetrics> getServerMetrics(){
        ResponseEntity<ServerMetrics> response;
        try{
            System.out.println("Server Metrics Request");
            response = metricService.compileMetrics();
            return response;
        } catch (Exception E) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

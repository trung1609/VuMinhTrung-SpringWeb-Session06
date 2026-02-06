package com.example.session06.controller;

import com.example.session06.model.dto.response.ApiResponse;
import com.example.session06.model.dto.response.ZoneStatisticsResponse;
import com.example.session06.service.ZoneService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ZoneController {
    @Autowired
    private ZoneService zoneService;

    @RequestMapping("api/v1/zones/stats")
    public ResponseEntity<ApiResponse<List<ZoneStatisticsResponse>>> getZoneStatisticsV1(){
        return new ResponseEntity<>(
                new ApiResponse<>(
                        "Get zone statistics v1 successfully",
                        true,
                        zoneService.getZoneStatisticsV1()
                ),
                HttpStatus.OK
        );
    }

    @RequestMapping("api/v2/zones/stats")
    public ResponseEntity<ApiResponse<List<ZoneStatisticsResponse>>> getZoneStatisticsV2(){
        return new ResponseEntity<>(
                new ApiResponse<>(
                        "Get zone statistics v1 successfully",
                        true,
                        zoneService.getZoneStatisticsV2()
                ),
                HttpStatus.OK
        );
    }
}

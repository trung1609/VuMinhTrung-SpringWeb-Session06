package com.example.session06.service;

import com.example.session06.model.dto.response.ZoneStatisticsResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ZoneService {

    List<ZoneStatisticsResponse> getZoneStatisticsV1();
    List<ZoneStatisticsResponse> getZoneStatisticsV2();
}

package com.example.session06.service.impl;

import com.example.session06.mapper.ZoneMapper;
import com.example.session06.model.dto.response.ZoneStatisticsResponse;
import com.example.session06.model.entity.Zone;
import com.example.session06.repository.ZoneRepository;
import com.example.session06.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneServiceImpl implements ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private ZoneMapper zoneMapper;

    @Override
    public List<ZoneStatisticsResponse> getZoneStatisticsV1() {
        List<Zone> zones = zoneRepository.findAll();

        for (Zone zone : zones) {
            int availableSpots = zone.getCapacity() - zone.getOccupiedSpots();
            ZoneStatisticsResponse response = zoneMapper.toDTO(zone);
            response.setAvailableSlots(availableSpots);
        }

        return zones.stream().map(zoneMapper::toDTO).toList();
    }
    @Override
    public List<ZoneStatisticsResponse> getZoneStatisticsV2(){
        return zoneRepository.getStatistics();
    }

}

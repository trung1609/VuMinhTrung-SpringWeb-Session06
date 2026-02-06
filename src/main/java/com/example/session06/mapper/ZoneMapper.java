package com.example.session06.mapper;

import com.example.session06.model.dto.response.ZoneStatisticsResponse;
import com.example.session06.model.entity.Zone;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ZoneMapper {
    @Autowired
    private ModelMapper modelMapper;

    public ZoneStatisticsResponse toDTO(Zone zone) {
        return ZoneStatisticsResponse.builder()
                .id(zone.getId())
                .zoneName(zone.getName())
                .capacity(zone.getCapacity())
                .occupiedSlots(zone.getOccupiedSpots())
                .availableSlots(zone.getCapacity() - zone.getOccupiedSpots())
                .build();
    }
}

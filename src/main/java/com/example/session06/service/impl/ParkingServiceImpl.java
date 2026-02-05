package com.example.session06.service.impl;

import com.example.session06.mapper.TicketMapper;
import com.example.session06.model.dto.request.TicketRequest;
import com.example.session06.model.dto.response.TicketResponse;
import com.example.session06.model.entity.ParkingTicket;
import com.example.session06.model.entity.Vehicle;
import com.example.session06.model.entity.Zone;
import com.example.session06.repository.ParkingRepository;
import com.example.session06.repository.VehicleRepository;
import com.example.session06.repository.ZoneRepository;
import com.example.session06.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private TicketMapper ticketMapper;

    @Override
    @Transactional
    public TicketResponse checkIn(TicketRequest ticketRequest) {
        Vehicle existingVehicle = vehicleRepository.findById(ticketRequest.getVehicleId()).orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + ticketRequest.getVehicleId()));
        Zone existingZone = zoneRepository.findById(ticketRequest.getZoneId()).orElseThrow(() -> new RuntimeException("Zone not found with ID: " + ticketRequest.getZoneId()));

        if(existingZone.getOccupiedSpots() <= 0){
            throw new RuntimeException("Zone is full");
        }

        existingZone.setOccupiedSpots(existingZone.getOccupiedSpots() - 1);

        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicket.setVehicle(existingVehicle);
        parkingTicket.setZone(existingZone);
        parkingTicket.setCheckInTime(LocalDateTime.now());
        ParkingTicket savedTicket = parkingRepository.save(parkingTicket);
        return ticketMapper.toDTO(savedTicket);
    }

    @Override
    public TicketResponse checkOut(Long vehicleId) {
        return null;
    }
}

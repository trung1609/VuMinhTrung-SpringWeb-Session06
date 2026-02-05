package com.example.session06.mapper;

import com.example.session06.model.dto.response.TicketResponse;
import com.example.session06.model.entity.ParkingTicket;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    @Autowired
    private ModelMapper modelMapper;

    public TicketResponse toDTO(ParkingTicket ticket) {
        return TicketResponse.builder()
                .id(ticket.getId())
                .licensePlate(ticket.getVehicle().getLicensePlate())
                .checkInTime(ticket.getCheckInTime())
                .checkOutTime(ticket.getCheckOutTime())
                .zoneName(ticket.getZone().getName())
                .build();
    }

    public ParkingTicket toEntity(TicketResponse ticketResponse) {
        return modelMapper.map(ticketResponse, ParkingTicket.class);
    }
}

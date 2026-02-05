package com.example.session06.service;

import com.example.session06.model.dto.request.TicketRequest;
import com.example.session06.model.dto.response.TicketResponse;

public interface ParkingService {
    TicketResponse checkIn(TicketRequest ticketRequest);
    TicketResponse checkOut(Long vehicleId);
}

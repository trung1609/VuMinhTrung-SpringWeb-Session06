package com.example.session06.service;

import com.example.session06.model.dto.request.PageRequestDTO;
import com.example.session06.model.dto.request.TicketRequest;
import com.example.session06.model.dto.response.PageResponse;
import com.example.session06.model.dto.response.TicketResponse;
import com.example.session06.model.dto.response.TicketSummaryResponse;

import java.time.LocalDate;
import java.util.List;

public interface ParkingService {
    TicketResponse checkIn(TicketRequest ticketRequest);
    TicketResponse checkOut(Long vehicleId);

    List<TicketSummaryResponse> getParkingTicketByCheckInTime();

    PageResponse<TicketResponse> findAllByLicensePlate(String licensePlate, LocalDate fromDate, LocalDate toDate, PageRequestDTO pageRequestDTO);
}

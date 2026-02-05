package com.example.session06.controller;

import com.example.session06.model.dto.request.TicketRequest;
import com.example.session06.model.dto.response.ApiResponse;
import com.example.session06.model.dto.response.TicketResponse;
import com.example.session06.service.impl.ParkingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    @Autowired
    private ParkingServiceImpl parkingService;

    @PostMapping("/check-in")
    public ResponseEntity<ApiResponse<TicketResponse>> checkIn(@RequestBody TicketRequest ticketRequest) {
        try {
            return new ResponseEntity<>(
                    new ApiResponse<>(
                            "Check in successfully",
                            true,
                            parkingService.checkIn(ticketRequest)
                    ),
                    HttpStatus.CREATED
            );
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    new ApiResponse<>(
                            e.getMessage(),
                            false,
                            null
                    ),
                    HttpStatus.NOT_FOUND
            );
        }
    }
}

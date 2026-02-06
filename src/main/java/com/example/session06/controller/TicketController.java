package com.example.session06.controller;

import com.example.session06.model.dto.request.TicketRequest;
import com.example.session06.model.dto.response.ApiResponse;
import com.example.session06.model.dto.response.TicketResponse;
import com.example.session06.model.dto.response.TicketSummaryResponse;
import com.example.session06.service.impl.ParkingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                    HttpStatus.OK
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

    @PostMapping("/check-out/{vehicleId}")
    public ResponseEntity<ApiResponse<TicketResponse>> checkOut(@PathVariable Long vehicleId) {
        try {
            return new ResponseEntity<>(
                    new ApiResponse<>(
                            "Check out successfully",
                            true,
                            parkingService.checkOut(vehicleId)
                    ),
                    HttpStatus.OK
            );
        }catch (RuntimeException e){
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

    @GetMapping("/summary")
    public ResponseEntity<ApiResponse<List<TicketSummaryResponse>>> getParkingTicketByCheckInTime(){
        return new ResponseEntity<>(
                new ApiResponse<>(
                        "Get parking ticket by check-in time successfully",
                        true,
                        parkingService.getParkingTicketByCheckInTime()
                ),
                HttpStatus.OK
        );
    }
}

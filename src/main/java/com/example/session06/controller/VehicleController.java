package com.example.session06.controller;

import com.example.session06.model.dto.request.PageRequestDTO;
import com.example.session06.model.dto.request.VehicleRequest;
import com.example.session06.model.dto.response.ApiResponse;
import com.example.session06.model.dto.response.PageResponse;
import com.example.session06.model.dto.response.VehicleResponse;
import com.example.session06.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<ApiResponse<VehicleResponse>> createVehicle(@RequestBody VehicleRequest vehicleRequest) {
        try {
            return new ResponseEntity<>(
                    new ApiResponse<>(
                            "Create vehicle successfully",
                            true,
                            vehicleService.createVehicle(vehicleRequest)
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
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<VehicleResponse>>> getPagedVehicles(
            @ModelAttribute PageRequestDTO pageRequestDTO,
            @RequestParam(required = false) String keyword
    ){
        return new ResponseEntity<>(
                new ApiResponse<>(
                        "Get vehicles successfully",
                        true,
                        vehicleService.getPagedVehicles(keyword, pageRequestDTO)
                ),
                HttpStatus.OK
        );
    }
}

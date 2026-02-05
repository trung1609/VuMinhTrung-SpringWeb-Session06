package com.example.session06.service;

import com.example.session06.model.dto.request.PageRequestDTO;
import com.example.session06.model.dto.request.VehicleRequest;
import com.example.session06.model.dto.response.PageResponse;
import com.example.session06.model.dto.response.VehicleResponse;

public interface VehicleService {
    VehicleResponse createVehicle(VehicleRequest vehicleRequest);

    PageResponse<VehicleResponse> getPagedVehicles(String keyword, PageRequestDTO pageRequestDTO);
}

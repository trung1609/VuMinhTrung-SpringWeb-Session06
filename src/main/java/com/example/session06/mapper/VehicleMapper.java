package com.example.session06.mapper;

import com.example.session06.model.dto.request.VehicleRequest;
import com.example.session06.model.dto.response.VehicleResponse;
import com.example.session06.model.entity.Vehicle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {

    @Autowired
    private ModelMapper modelMapper;

    public VehicleResponse toDTO(Vehicle vehicle) {
        return modelMapper.map(vehicle, VehicleResponse.class);
    }

    public Vehicle toEntity(VehicleRequest vehicleRequest) {
        return modelMapper.map(vehicleRequest, Vehicle.class);
    }
}

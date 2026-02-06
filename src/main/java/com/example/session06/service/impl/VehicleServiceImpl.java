package com.example.session06.service.impl;

import com.example.session06.mapper.PageMapper;
import com.example.session06.mapper.VehicleMapper;
import com.example.session06.model.dto.request.PageRequestDTO;
import com.example.session06.model.dto.request.VehicleRequest;
import com.example.session06.model.dto.response.PageResponse;
import com.example.session06.model.dto.response.VehicleResponse;
import com.example.session06.model.entity.Vehicle;
import com.example.session06.repository.VehicleRepository;
import com.example.session06.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private PageMapper pageMapper;

    @Autowired
    private VehicleMapper vehicleMapper;

    @Override
    public VehicleResponse createVehicle(VehicleRequest vehicleRequest) {
        VehicleResponse vehicleResponse = vehicleRepository.findByLicensePlate(vehicleRequest.getLicensePlate());
        if (vehicleResponse != null){
            throw new RuntimeException("Vehicle with license plate " + vehicleRequest.getLicensePlate() + " already exists");
        }
        Vehicle vehicle = vehicleRepository.save(vehicleMapper.toEntity(vehicleRequest));
        vehicleResponse = vehicleMapper.toDTO(vehicle);
        return vehicleResponse;
    }

    @Override
    public PageResponse<VehicleResponse> getPagedVehicles(String keyword, PageRequestDTO pageRequestDTO) {
        Sort sort;
        if (pageRequestDTO.getSortBy() == null || pageRequestDTO.getSortBy().isBlank()){
            sort = Sort.unsorted();
        }else {
            sort = Sort.by(pageRequestDTO.getSortBy());
        }

        if(pageRequestDTO.getDirection() == null || pageRequestDTO.getDirection().isBlank()){
            sort = Sort.unsorted();
        }else {
            sort = Sort.by(pageRequestDTO.getDirection());
        }

        if(pageRequestDTO.getPage() == null || pageRequestDTO.getPage() < 0){
            pageRequestDTO.setPage(0);
        }

        if (pageRequestDTO.getSize() == null || pageRequestDTO.getSize() < 0){
            pageRequestDTO.setSize(5);
        }

        Pageable pageable = PageRequest.of(pageRequestDTO.getPage(), pageRequestDTO.getSize(), sort);
        Page<VehicleResponse> responses = vehicleRepository.findAllByKeyword(keyword, pageable);
        return pageMapper.toPageResponse(responses);
    }
}

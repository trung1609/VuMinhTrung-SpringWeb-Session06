package com.example.session06.service.impl;

import com.example.session06.mapper.PageMapper;
import com.example.session06.mapper.TicketMapper;
import com.example.session06.model.dto.request.PageRequestDTO;
import com.example.session06.model.dto.request.TicketRequest;
import com.example.session06.model.dto.response.PageResponse;
import com.example.session06.model.dto.response.TicketResponse;
import com.example.session06.model.dto.response.TicketSummaryResponse;
import com.example.session06.model.entity.ParkingTicket;
import com.example.session06.model.entity.Vehicle;
import com.example.session06.model.entity.Zone;
import com.example.session06.repository.ParkingRepository;
import com.example.session06.repository.VehicleRepository;
import com.example.session06.repository.ZoneRepository;
import com.example.session06.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private PageMapper pageMapper;

    @Autowired
    private TicketMapper ticketMapper;

    @Override
    @Transactional
    public TicketResponse checkIn(TicketRequest ticketRequest) {
        // Kiểm tra Vehicle có tồn tại không
        Vehicle existingVehicle = vehicleRepository.findById(ticketRequest.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + ticketRequest.getVehicleId()));

        // Kiểm tra Zone có tồn tại không
        Zone existingZone = zoneRepository.findById(ticketRequest.getZoneId())
                .orElseThrow(() -> new RuntimeException("Zone not found with ID: " + ticketRequest.getZoneId()));

        // Kiểm tra xem Zone còn trống chỗ không
        if (existingZone.getOccupiedSpots() >= existingZone.getCapacity()) {
            throw new RuntimeException("Zone is full");
        }

        // Tạo mới ParkingTicket
        ParkingTicket parkingTicket = new ParkingTicket();
        // Gắn Vehicle và Zone tương ứng
        parkingTicket.setVehicle(existingVehicle);
        parkingTicket.setZone(existingZone);
        // Gán checkInTime là thời gian hiện tại
        parkingTicket.setCheckInTime(LocalDateTime.now());

        // Cập nhật lại occupiedSpots của Zone
        existingZone.setOccupiedSpots(existingZone.getOccupiedSpots() + 1);
        zoneRepository.save(existingZone);

        // Lưu thông tin vào Database
        ParkingTicket savedTicket = parkingRepository.save(parkingTicket);
        return ticketMapper.toDTO(savedTicket);
    }

    @Override
    @Transactional
    public TicketResponse checkOut(Long vehicleId) {
        // Tìm kiếm ParkingTicket gần nhất của xe này mà chưa có checkOutTime
        ParkingTicket parkingTicket = parkingRepository.findLatestActiveTicketByVehicleId(vehicleId).stream().findFirst()
                .orElseThrow(() -> new RuntimeException("No active parking ticket found for vehicle id: " + vehicleId));

        // Cập nhật checkOutTime là thời gian hiện tại
        parkingTicket.setCheckOutTime(LocalDateTime.now());

        // Cập nhật lại occupiedSpots của Zone
        Zone zone = parkingTicket.getZone();
        zone.setOccupiedSpots(zone.getOccupiedSpots() - 1);
        zoneRepository.save(zone);

        // Lưu thông tin vào Database
        ParkingTicket savedTicket = parkingRepository.save(parkingTicket);
        return ticketMapper.toDTO(savedTicket);
    }

    @Override
    public List<TicketSummaryResponse> getParkingTicketByCheckInTime() {
        LocalDate today = LocalDate.now();
        return parkingRepository.getParkingTicketByCheckInTime(today.atStartOfDay(), today.plusDays(1).atStartOfDay());
    }

    @Override
    public PageResponse<TicketResponse> findAllByLicensePlate(String licensePlate,LocalDate fromDate, LocalDate toDate, PageRequestDTO pageRequestDTO) {
        Sort sort;
        if (pageRequestDTO.getSortBy() == null || pageRequestDTO.getSortBy().isBlank()) {
            sort = Sort.unsorted();
        } else {
            sort = Sort.by(pageRequestDTO.getSortBy());
        }

        if (pageRequestDTO.getDirection() == null || pageRequestDTO.getDirection().isBlank()) {
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

        Page<TicketResponse> responses = parkingRepository.findAllByLicensePlate(licensePlate, fromDate.atStartOfDay(), toDate.atStartOfDay(), pageable);

        return pageMapper.toPageResponse(responses);
    }
}

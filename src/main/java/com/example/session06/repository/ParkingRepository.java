package com.example.session06.repository;

import com.example.session06.model.dto.response.TicketResponse;
import com.example.session06.model.dto.response.TicketSummaryResponse;
import com.example.session06.model.entity.ParkingTicket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingRepository extends JpaRepository<ParkingTicket, Long> {

    @Query("select pt from ParkingTicket pt where pt.vehicle.id = :vehicleId and pt.checkOutTime is null order by pt.checkInTime DESC limit 1")
    Optional<ParkingTicket> findLatestActiveTicketByVehicleId(@Param("vehicleId") Long vehicleId);

    @Query("select new com.example.session06.model.dto.response.TicketSummaryResponse(pt.id, pt.vehicle.licensePlate, pt.zone.name, pt.checkInTime, pt.checkOutTime) from ParkingTicket pt where pt.checkInTime between :start and :end")
    List<TicketSummaryResponse> getParkingTicketByCheckInTime(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("select new com.example.session06.model.dto.response.TicketResponse(pt.id, pt.vehicle.licensePlate, pt.vehicle.type, pt.zone.name, pt.checkInTime, pt.checkOutTime) from ParkingTicket pt where pt.vehicle.licensePlate = :licensePlate and pt.checkInTime >= :start and pt.checkInTime <= :end")
    Page<TicketResponse> findAllByLicensePlate(@Param("licensePlate") String licensePlate, LocalDateTime start, LocalDateTime end, Pageable pageable);
}

package com.example.session06.repository;

import com.example.session06.model.entity.ParkingTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingRepository extends JpaRepository<ParkingTicket, Long> {

    @Query("select pt from ParkingTicket pt where pt.vehicle.id = :vehicleId and pt.checkOutTime is null order by pt.checkInTime DESC limit 1")
    Optional<ParkingTicket> findLatestActiveTicketByVehicleId(@Param("vehicleId") Long vehicleId);

}

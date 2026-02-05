package com.example.session06.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long id;

    @Column(name = "vehicle_license_plate", nullable = false)
    private String licensePlate;

    @Column(name = "vehicle_color", nullable = false)
    private String color;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type", nullable = false)
    private VehicleType type;

    @OneToMany(mappedBy = "vehicle")
    private List<ParkingTicket> parkingTickets = new ArrayList<>();
}

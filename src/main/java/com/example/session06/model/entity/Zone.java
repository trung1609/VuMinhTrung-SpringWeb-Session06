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
@Table(name = "zones")
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zone_id")
    private Long id;

    @Column(name = "zone_name", nullable = false)
    private String name;

    @Column(name = "zone_capacity", nullable = false)
    private Integer capacity;

    @Column(name = "zone_occupied_spots", nullable = false)
    private Integer occupiedSpots;

    @OneToMany(mappedBy = "zone")
    private List<ParkingTicket> parkingTickets = new ArrayList<>();
}

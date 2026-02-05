package com.example.session06.model.dto.response;

import com.example.session06.model.entity.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponse {
    private Long id;
    private String licensePlate;
    private String color;
    private VehicleType type;
}

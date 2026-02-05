package com.example.session06.model.dto.request;

import com.example.session06.model.entity.VehicleType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequest {
    private String licensePlate;
    private String color;
    private VehicleType type;
}

package com.example.session06.model.dto.response;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZoneStatisticsResponse {
    private Long id;
    private String zoneName;
    private Integer capacity;
    private Integer occupiedSlots;
    private Integer availableSlots;
}

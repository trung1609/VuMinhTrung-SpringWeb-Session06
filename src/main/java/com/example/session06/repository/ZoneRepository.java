package com.example.session06.repository;

import com.example.session06.model.dto.response.ZoneStatisticsResponse;
import com.example.session06.model.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {

    @Query("select new com.example.session06.model.dto.response.ZoneStatisticsResponse(z.id, z.name, z.capacity, z.occupiedSpots, (z.capacity - z.occupiedSpots)) from Zone z order by z.id")
    List<ZoneStatisticsResponse> getStatistics();
}

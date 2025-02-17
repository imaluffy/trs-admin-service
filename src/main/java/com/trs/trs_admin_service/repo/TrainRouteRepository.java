package com.trs.trs_admin_service.repo;

import com.trs.trs_admin_service.model.TrainRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainRouteRepository extends JpaRepository<TrainRoute, Long> {
    Optional<TrainRoute> findBySourceAndDestination(String source, String destination);
}

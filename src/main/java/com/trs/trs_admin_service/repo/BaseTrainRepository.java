package com.trs.trs_admin_service.repo;

import com.trs.trs_admin_service.model.TrainStopBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseTrainRepository extends JpaRepository<TrainStopBase, Integer> {
}

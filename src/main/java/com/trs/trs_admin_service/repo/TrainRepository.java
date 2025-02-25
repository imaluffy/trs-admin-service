package com.trs.trs_admin_service.repo;

import com.trs.trs_admin_service.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends JpaRepository<Train, Integer> {
    Train findByTrainNumber(Integer trainNumber);
}
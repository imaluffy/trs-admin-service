package com.trs.trs_admin_service.service;

import com.trs.trs_admin_service.model.TrainStopBase;
import com.trs.trs_admin_service.repo.BaseTrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseTrainService {
    @Autowired
    BaseTrainRepository baseTrainRepository;


    public void addBaseTrain(TrainStopBase trainStopBase) {
        baseTrainRepository.save(trainStopBase);
    }
}

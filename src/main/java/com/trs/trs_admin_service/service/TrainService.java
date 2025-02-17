package com.trs.trs_admin_service.service;

import com.trs.trs_admin_service.model.Train;
import com.trs.trs_admin_service.repo.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainService {
    @Autowired
    TrainRepository trainRepository;

    public void addTrain(Train train) {
        trainRepository.save(train);
    }

    public Train getTrain(Integer trainNumber) {
        Optional<Train> optionalTrain = trainRepository.findByTrainNumber(trainNumber);
        return optionalTrain.orElse(null);
    }
}

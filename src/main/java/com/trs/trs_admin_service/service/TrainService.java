package com.trs.trs_admin_service.service;

import com.trs.trs_admin_service.model.Train;
import com.trs.trs_admin_service.model.dto.TrainDTO;
import com.trs.trs_admin_service.repo.TrainRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainService {
    @Autowired
    TrainRepository trainRepository;

    @Autowired
    ModelMapper modelMapper;

    public void addTrain(TrainDTO train) {
        Train trainEntity = modelMapper.map(train, Train.class);
        trainRepository.save(trainEntity);
    }

    public TrainDTO getTrain(Integer trainNumber) {
        Optional<Train> optionalTrain = trainRepository.findByTrainNumber(trainNumber);
        if(optionalTrain.isPresent()){
            return modelMapper.map(optionalTrain.get(), TrainDTO.class);
        }
        return null;
    }
}

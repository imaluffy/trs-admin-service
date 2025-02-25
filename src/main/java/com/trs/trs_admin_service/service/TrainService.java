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

    public TrainDTO addTrain(TrainDTO train) {
        Train trainEntity = modelMapper.map(train, Train.class);
        return modelMapper.map(trainRepository.save(trainEntity),TrainDTO.class);
    }

    public TrainDTO updateTrain(TrainDTO trainDTO) {
        if(getTrain(trainDTO.getTrainNumber())!=null) {
            return addTrain(trainDTO);
        }

        return null;
    }

    public TrainDTO getTrain(Integer trainNumber) {
        Train resTrain = trainRepository.findByTrainNumber(trainNumber);
        if(resTrain != null){
            return modelMapper.map(resTrain, TrainDTO.class);
        }
        return null;
    }

    public void removeTrain(Integer trainNumber) {
        trainRepository.delete(trainRepository.findByTrainNumber(trainNumber));
    }
}

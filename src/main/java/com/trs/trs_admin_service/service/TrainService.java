package com.trs.trs_admin_service.service;

import com.trs.trs_admin_service.model.Train;
import com.trs.trs_admin_service.model.TrainStop;
import com.trs.trs_admin_service.model.dto.TrainDTO;
import com.trs.trs_admin_service.model.dto.TrainStopDTO;
import com.trs.trs_admin_service.repo.TrainRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    @Transactional
    public List<TrainStop> addStopInTrain(TrainStopDTO trainStopDTO, Integer trainNum) {
        TrainStop trainStop=modelMapper.map(trainStopDTO, TrainStop.class);
        Optional<Train> trainEntity=trainRepository.findById(trainNum);

        if(trainEntity.isPresent()) {
            trainEntity.get().getTrainStops().add(trainStop);
            return trainEntity.get().getTrainStops();
        }

        return null;
    }

    @Transactional
    public List<TrainStop> updateStopInTrain(TrainStopDTO trainStopDTO, Integer trainNum) {
        TrainStop trainStop=modelMapper.map(trainStopDTO, TrainStop.class);
        Optional<Train> trainEntity=trainRepository.findById(trainNum);
        if(trainEntity.isPresent()){
            List<TrainStop> trainStopList= new ArrayList<>(trainEntity.get().getTrainStops()
                    .stream()
                    .filter(trainStopVal -> !trainStopVal.getStationName().equals(trainStop.getStationName()))
                    .toList());

            trainStopList.add(trainStop);
            trainEntity.get().setTrainStops(trainStopList);
            return trainEntity.get().getTrainStops();
        }
        return  null;
    }

//    @Transactional
//    public List<TrainStop> updateStopInTrain(TrainStopDTO trainStopDTO, Integer trainNum) {
//        TrainStop trainStop=modelMapper.map(trainStopDTO, TrainStop.class);
//        Optional<Train> trainEntity=trainRepository.findById(trainNum);
//        if(trainEntity.isPresent()){
//
//            trainEntity.get().getTrainStops()
//                    .stream()
//                    .map(trainStopVal -> {
//                        if (trainStopVal.getStationName().equals(trainStop.getStationName())) {
//                            trainStopVal.setAcFare(trainStop.getAcFare());
//                            trainStopVal.setSlFare(trainStop.getSlFare());
//                            trainStopVal.setArrivalTime(trainStop.getArrivalTime());
//                            trainStopVal.setDateOfJourney(trainStop.getDateOfJourney());
//                            trainStopVal.setAcSeatLeftFrom(trainStop.getAcSeatLeftFrom());
//                            trainStopVal.setAcSeatLeftTill(trainStop.getAcSeatLeftTill());
//                            trainStopVal.setSlSeatLeftFrom(trainStopDTO.getSlSeatLeftFrom());
//                            trainStopVal.setSlSeatLeftTill(trainStop.getSlSeatLeftTill());
//                        }
//
//                    });
//
//
//            return trainEntity.get().getTrainStops();
//        }
//        return  null;
//    }
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

package com.trs.trs_admin_service.service;

import com.trs.trs_admin_service.model.RequestTemplate;
import com.trs.trs_admin_service.model.Train;
import com.trs.trs_admin_service.model.TrainStop;
import com.trs.trs_admin_service.model.dto.TrainDTO;
import com.trs.trs_admin_service.model.dto.TrainStopDTO;
import com.trs.trs_admin_service.repo.TrainRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TrainService {
    @Autowired
    TrainRepository trainRepository;

    @Autowired
    ModelMapper modelMapper;

    public TrainDTO addTrain(TrainDTO train) {
        Train trainEntity = modelMapper.map(train, Train.class);
        return modelMapper.map(trainRepository.save(trainEntity),TrainDTO.class);
    }

    public TrainDTO getTrain(Integer trainNumber) {
        Train resTrain = trainRepository.findByTrainNumber(trainNumber);
        if(resTrain != null){
            return modelMapper.map(resTrain, TrainDTO.class);
        }
        return null;
    }

    @Transactional
    public TrainDTO updateTrain(TrainDTO trainDTO) {

        Train updatedTrain=modelMapper.map(trainDTO, Train.class);
        Optional<Train> existingTrain=trainRepository.findById(trainDTO.getTrainNumber());

        if(existingTrain.isPresent()){
            existingTrain.get().setTrainName(updatedTrain.getTrainName() != null ? updatedTrain.getTrainName() : existingTrain.get().getTrainName());
            existingTrain.get().setTrainSource(updatedTrain.getTrainSource() != null ? updatedTrain.getTrainSource() : existingTrain.get().getTrainSource());
            existingTrain.get().setTrainDestination(updatedTrain.getTrainDestination() != null ? updatedTrain.getTrainDestination() : existingTrain.get().getTrainDestination());
            existingTrain.get().setSlCoachesNum(updatedTrain.getSlCoachesNum() != null ? updatedTrain.getSlCoachesNum() : existingTrain.get().getSlCoachesNum());
            existingTrain.get().setAcCoachesNum(updatedTrain.getAcCoachesNum() != null ? updatedTrain.getAcCoachesNum() : existingTrain.get().getAcCoachesNum());
            existingTrain.get().setTrainBaseFare(updatedTrain.getTrainBaseFare() != null ? updatedTrain.getTrainBaseFare() : existingTrain.get().getTrainBaseFare());
            existingTrain.get().setTrainStatus(updatedTrain.getTrainStatus() != null ? updatedTrain.getTrainStatus() : existingTrain.get().getTrainStatus());

            if (updatedTrain.getTrainStops() != null && !updatedTrain.getTrainStops().isEmpty()) {
                existingTrain.get().setTrainStops(updatedTrain.getTrainStops());
            }
        }

        return modelMapper.map(existingTrain,TrainDTO.class);

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
    public List<TrainStop> updateStopInTrain1(TrainStopDTO trainStopDTO, Integer trainNum) {
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

    @Transactional
    public List<TrainStop> updateStopInTrain(TrainStopDTO trainStopDTO, Integer trainNum) {
        TrainStop trainStop=modelMapper.map(trainStopDTO, TrainStop.class);
        Optional<Train> trainEntity=trainRepository.findById(trainNum);
        if(trainEntity.isPresent()){

            trainEntity.get().getTrainStops()
                    .forEach(trainStopVal -> {
                        if (trainStopVal.getStationName().equals(trainStop.getStationName())) {
                            trainStopVal.setAcFare(Optional.ofNullable(trainStop.getAcFare()).orElse(trainStopVal.getAcFare()));
                            trainStopVal.setSlFare(Optional.ofNullable(trainStop.getSlFare()).orElse(trainStopVal.getSlFare()));
                            trainStopVal.setDistanceFromSourceInKms(Optional.ofNullable(trainStop.getDistanceFromSourceInKms()).orElse(trainStopVal.getDistanceFromSourceInKms()));
                            trainStopVal.setArrivalTime(Optional.ofNullable(trainStop.getArrivalTime()).orElse(trainStopVal.getArrivalTime()));
                            trainStopVal.setDepartureTime(Optional.ofNullable(trainStop.getDepartureTime()).orElse(trainStopVal.getDepartureTime()));
                            trainStopVal.setAcSeatLeftFrom(Optional.ofNullable(trainStop.getAcSeatLeftFrom()).orElse(trainStopVal.getAcSeatLeftFrom()));
                            trainStopVal.setAcSeatLeftTill(Optional.ofNullable(trainStop.getAcSeatLeftTill()).orElse(trainStopVal.getAcSeatLeftTill()));
                            trainStopVal.setSlSeatLeftFrom(Optional.ofNullable(trainStop.getSlSeatLeftFrom()).orElse(trainStopVal.getSlSeatLeftFrom()));
                            trainStopVal.setSlSeatLeftTill(Optional.ofNullable(trainStop.getSlSeatLeftTill()).orElse(trainStopVal.getSlSeatLeftTill()));

                        }
                    });

            return trainEntity.get().getTrainStops();
        }
        return  null;
    }




    public Integer removeTrain(Integer trainNumber) {
        if(trainRepository.existsById(trainNumber)){
            trainRepository.deleteById(trainNumber);
            return 1;
        }
        else{
            return 0;
        }
    }

    @Transactional
    public Boolean removeTrainStop(RequestTemplate requestTemplate) {
        Optional<Train> trainEntity= trainRepository.findById(requestTemplate.getTrainNumber());

        if(trainEntity.isPresent()) {
            List<TrainStop> listTrain = trainEntity.get().getTrainStops()
                    .stream()
                    .filter(dateOfJourney -> {
                        String s1 = dateOfJourney.getDateOfJourney().toString();
                        String s2 = requestTemplate.getDateOfJourney().toString();
                        return !s1.equals(s2);
                    }).toList();
            trainEntity.get().setTrainStops(listTrain);
            return true;
        }
        return false;
    }

    @Transactional
    public List<TrainStop> removeTrainStopByStation(RequestTemplate requestTemplate) {
        Optional<Train> trainEntity= trainRepository.findById(requestTemplate.getTrainNumber());

        if(trainEntity.isPresent()){
            List<TrainStop> listTrain = trainEntity.get().getTrainStops()
                    .stream()
                    .filter(trainStop -> {
                        boolean dateOfJourneyCheck = trainStop.getDateOfJourney().toString()
                                .equals(requestTemplate.getDateOfJourney().toString());

                        boolean stationCheck = trainStop.getStationName()
                                .equals(requestTemplate.getStationName());

                        return !(dateOfJourneyCheck && stationCheck);
                    })
                    .toList();

            trainEntity.get().setTrainStops(listTrain);
            return trainEntity.get().getTrainStops();
        }
        return null;
    }


}

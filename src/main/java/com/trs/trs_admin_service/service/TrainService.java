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
                            trainStopVal.setAcFare(trainStop.getAcFare());
                            trainStopVal.setSlFare(trainStop.getSlFare());
                            trainStopVal.setArrivalTime(trainStop.getArrivalTime());
                            trainStopVal.setDateOfJourney(trainStop.getDateOfJourney());
                            trainStopVal.setAcSeatLeftFrom(trainStop.getAcSeatLeftFrom());
                            trainStopVal.setAcSeatLeftTill(trainStop.getAcSeatLeftTill());
                            trainStopVal.setSlSeatLeftFrom(trainStopDTO.getSlSeatLeftFrom());
                            trainStopVal.setSlSeatLeftTill(trainStop.getSlSeatLeftTill());
                        }
                    });

//            trainEntity.get().setTrainStops(trainStopList);
            return trainEntity.get().getTrainStops();
        }
        return  null;
    }




    public void removeTrain(Integer trainNumber) {
        try {
            trainRepository.deleteById(trainNumber);
        }
        catch (Exception ignored){
            log.error("Unable to remove train");
        }
    }

    @Transactional
    public List<TrainStop> removeTrainStop(RequestTemplate requestTemplate) {
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
            return trainEntity.get().getTrainStops();
        }
        return null;
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

//    @Transactional
//    public void addTrainForDate(RequestTemplate requestTemplate) {
//
//        List<String> trainStopList=trainRepository.findById(requestTemplate.getTrainNumber())
//                .get().getTrainStops().stream().map(trainStop -> trainStop.getStationName()).toList();
//
//        List<TrainStop> trainStopList1;
//        for(String train:trainStopList){
//            TrainStop trainStop=new TrainStop(requestTemplate.getDateOfJourney(), requestTemplate.getStationName(), "08:30:00",50,50,120,120,120,120);
//        }
//
//    }
}

package com.trs.trs_admin_service.service;

import com.trs.trs_admin_service.model.RequestTemplate;
import com.trs.trs_admin_service.model.Train;
import com.trs.trs_admin_service.model.TrainStop;
import com.trs.trs_admin_service.model.TrainStopBase;
import com.trs.trs_admin_service.repo.BaseTrainRepository;
import com.trs.trs_admin_service.repo.TrainRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BaseTrainService {
    @Autowired
    BaseTrainRepository baseTrainRepository;

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    TrainService trainService;


    public void addBaseTrain(TrainStopBase trainStopBase) {
        baseTrainRepository.save(trainStopBase);
    }

    @Transactional
    public List<TrainStop> addTrainStopsByDate(RequestTemplate requestTemplate){
        Optional<TrainStopBase> trainStopBase =  baseTrainRepository.findById(requestTemplate.getTrainNumber());
        Optional<Train> trainEntity= trainRepository.findById(requestTemplate.getTrainNumber());

        if(trainStopBase.isPresent()) {
            List<TrainStop> trainStopList = trainStopBase.get().getTrainStops()
                    .stream()
                    .map(trainStop -> {
                        TrainStop newStop = new TrainStop();
                        newStop.setDateOfJourney(requestTemplate.getDateOfJourney());
                        newStop.setStationName(trainStop.getStationName());
                        newStop.setSlFare(trainStop.getSlFare());
                        newStop.setAcFare(trainStop.getAcFare());
                        newStop.setArrivalTime(trainStop.getArrivalTime());
                        newStop.setSlSeatLeftFrom(trainStop.getSlSeatLeftFrom());
                        newStop.setSlSeatLeftTill(trainStop.getSlSeatLeftTill());
                        newStop.setAcSeatLeftTill(trainStop.getAcSeatLeftTill());
                        newStop.setAcSeatLeftFrom(trainStop.getAcSeatLeftFrom());
                        return newStop;
                    })
                    .toList();

            if(trainEntity.isPresent()) {
                trainEntity.get().getTrainStops().addAll(trainStopList);
                return trainEntity.get().getTrainStops();
            }

        }

      return null;
    }

    @Scheduled(cron = "0 0 * * *")
    public void scheduledTrainStopRemover(){
        LocalDate date_to_remove = LocalDate.now().minusDays(1);

        baseTrainRepository.findAll().forEach(
                baseTrains ->{
                    RequestTemplate deleteStopRequest = new RequestTemplate(baseTrains.getTrainNumber(), date_to_remove, null);
                    trainService.removeTrainStop(deleteStopRequest);
                }
        );
    }

}

package com.trs.trs_admin_service.service;

import com.trs.trs_admin_service.model.TrainRoute;
import com.trs.trs_admin_service.repo.TrainRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainRouteService {

    @Autowired
    TrainRouteRepository trainRouteRepository;

    public void addRoute(TrainRoute route) {
        trainRouteRepository.save(route);
        System.out.println("Route added");
    }

    public TrainRoute getRoute(String source, String destination) {
        Optional<TrainRoute> trainRoute = trainRouteRepository.findBySourceAndDestination(source, destination);
        return trainRoute.orElse(null);
    }
}

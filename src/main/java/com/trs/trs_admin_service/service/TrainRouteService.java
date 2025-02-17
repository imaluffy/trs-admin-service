package com.trs.trs_admin_service.service;

import com.trs.trs_admin_service.model.TrainRoute;
import com.trs.trs_admin_service.model.dto.TrainRouteDTO;
import com.trs.trs_admin_service.repo.TrainRouteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainRouteService {

    @Autowired
    TrainRouteRepository trainRouteRepository;

    @Autowired
    ModelMapper modelMapper;

    public void addRoute(TrainRouteDTO route) {
        TrainRoute routeEntity = modelMapper.map(route, TrainRoute.class);
        trainRouteRepository.save(routeEntity);
//        System.out.println("Route added");
    }

    public TrainRouteDTO getRoute(String source, String destination) {
        Optional<TrainRoute> trainRoute = trainRouteRepository.findBySourceAndDestination(source, destination);
        if(trainRoute.isPresent()){
            return modelMapper.map(trainRoute.get(), TrainRouteDTO.class);
        }
        return null;
    }
}

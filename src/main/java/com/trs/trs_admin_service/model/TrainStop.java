package com.trs.trs_admin_service.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.sql.Time;

@Data
@Embeddable
public class TrainStop {
    String stationName;
    Time arrivalTime;
}

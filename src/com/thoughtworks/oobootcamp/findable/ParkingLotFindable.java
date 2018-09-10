package com.thoughtworks.oobootcamp.findable;

import com.thoughtworks.oobootcamp.ParkingLot;

import java.util.List;

public interface ParkingLotFindable {
    ParkingLot find(List<ParkingLot> parkingLots);
}

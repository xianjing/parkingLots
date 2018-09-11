package com.thoughtworks.oobootcamp.findable;

import com.thoughtworks.oobootcamp.Parkable;
import com.thoughtworks.oobootcamp.ParkingLot;

import java.util.List;

public interface ParkingLotFindable {
    Parkable find(List<ParkingLot> parkables);
}

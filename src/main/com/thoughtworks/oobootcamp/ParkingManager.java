package com.thoughtworks.oobootcamp;

import com.thoughtworks.oobootcamp.exception.ParkingLotIsFullException;

import java.util.List;
import java.util.Optional;

public class ParkingManager {
    private List<ParkingBoy> parkingBoys;
    private List<ParkingLot> parkingLots;


    public ParkingManager(List<ParkingBoy> parkingBoys, List<ParkingLot> parkingManagerLots) {
        this.parkingBoys = parkingBoys;
        this.parkingLots = parkingManagerLots;
    }

    public Ticket park(Car car) {
        Optional<ParkingBoy> first = parkingBoys.stream().filter(p -> p.getAvailableLots() > 0).findFirst();
        if (first.isPresent()) {
            return first.get().park(car);
        }

        Optional<ParkingLot> optional = parkingLots.stream().filter(p -> p.getAvailableLots() > 0).findFirst();
        if(optional.isPresent()){
            return optional.get().park(car);
        }

        throw new ParkingLotIsFullException();
    }
}

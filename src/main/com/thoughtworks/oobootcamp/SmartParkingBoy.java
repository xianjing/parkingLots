package com.thoughtworks.oobootcamp;

import com.thoughtworks.oobootcamp.exception.TicketIsInvalidException;
import com.thoughtworks.oobootcamp.findable.ParkingLotFindable;

import java.util.List;
import java.util.Optional;

public class SmartParkingBoy {
    private final ParkingLotFindable parkingLotFindable;
    private List<ParkingLot> parkingLots;

    public SmartParkingBoy(List<ParkingLot> parkingLots, ParkingLotFindable parkingLotFinder) {
        this.parkingLots = parkingLots;
        parkingLotFindable = parkingLotFinder;
    }

    public Ticket park(Car car) {
        ParkingLot parkingLot = parkingLotFindable.find(parkingLots);
        return parkingLot.park(car);
    }

    public Car pickUp(Ticket ticket) {
        Optional<ParkingLot> first = parkingLots.stream()
                .filter(lot -> lot.isTicketValid(ticket))
                .findFirst();
        if(first.isPresent()){
            return first.get().pickUp(ticket);
        }
        throw new TicketIsInvalidException();
    }
}

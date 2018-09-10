package com.thoughtworks.oobootcamp;

import com.thoughtworks.oobootcamp.findable.MaxAvailableParkingLotFinder;
import com.thoughtworks.oobootcamp.exception.TicketIsInvalidException;

import java.util.List;
import java.util.Optional;

public class SmartParkingBoy {
    private final MaxAvailableParkingLotFinder maxAvailableParkingLotFinder;
    private List<ParkingLot> parkingLots;

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
        maxAvailableParkingLotFinder = new MaxAvailableParkingLotFinder();
    }

    public Ticket park(Car car) {
        ParkingLot parkingLot = maxAvailableParkingLotFinder.find(parkingLots);
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

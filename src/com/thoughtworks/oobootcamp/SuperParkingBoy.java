package com.thoughtworks.oobootcamp;

import com.thoughtworks.oobootcamp.exception.TicketIsInvalidException;
import com.thoughtworks.oobootcamp.findable.MaxVacancyRateParkingLotFinder;
import com.thoughtworks.oobootcamp.findable.ParkingLotFindable;

import java.util.List;
import java.util.Optional;

public class SuperParkingBoy {
    private List<ParkingLot> parkingLots;
    private final ParkingLotFindable findable;

    public SuperParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
        findable = new MaxVacancyRateParkingLotFinder();
    }

    public Ticket park(Car car) {
        ParkingLot parkingLot = findable.find(parkingLots);
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

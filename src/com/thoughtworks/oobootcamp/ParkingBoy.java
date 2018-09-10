package com.thoughtworks.oobootcamp;

import com.thoughtworks.oobootcamp.findable.OrderedParkingLotFinder;
import com.thoughtworks.oobootcamp.exception.TicketIsInvalidException;

import java.util.List;
import java.util.Optional;

public class ParkingBoy {
    private final OrderedParkingLotFinder orderedParkingLotFinder;
    private List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
        orderedParkingLotFinder = new OrderedParkingLotFinder();
    }

    public Ticket park(Car car) {
        ParkingLot parkingLot = orderedParkingLotFinder.find(parkingLots);
        return parkingLot.park(car);
    }

    public Car pickUp(Ticket ticket) {
        Optional<ParkingLot> first = parkingLots.stream().filter(lot -> lot.isTicketValid(ticket)).findFirst();
        if(first.isPresent()){
            return first.get().pickUp(ticket);
        }
        throw new TicketIsInvalidException();
    }
}

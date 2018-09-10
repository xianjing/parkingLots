package com.thoughtworks.oobootcamp;

import com.thoughtworks.oobootcamp.exception.ParkingLotIsFullException;
import com.thoughtworks.oobootcamp.exception.TicketIsInvalidException;

import java.util.List;
import java.util.Optional;

public class ParkingBoy {
    private List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        ParkingLot parkingLot = findParkingLot();
        return parkingLot.park(car);
    }

    private ParkingLot findParkingLot() {
        Optional<ParkingLot> first = parkingLots.stream().filter(lot -> lot.getAvailableLots() > 0).findFirst();

        if (!first.isPresent()) {
            throw new ParkingLotIsFullException();
        }
        return first.get();
    }

    public Car pickUp(Ticket ticket) {
        Optional<ParkingLot> first = parkingLots.stream().filter(lot -> lot.isTicketValid(ticket)).findFirst();
        if(first.isPresent()){
            return first.get().pickUp(ticket);
        }
        throw new TicketIsInvalidException();
    }
}

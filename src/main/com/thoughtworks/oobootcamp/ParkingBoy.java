package com.thoughtworks.oobootcamp;

import com.thoughtworks.oobootcamp.exception.TicketIsInvalidException;
import com.thoughtworks.oobootcamp.findable.ParkingLotFindable;

import java.util.List;
import java.util.Optional;

public class ParkingBoy implements Parkable{
    private final ParkingLotFindable parkingLotFindable;
    private List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots, ParkingLotFindable parkingLotFindable) {
        this.parkingLots = parkingLots;
        this.parkingLotFindable = parkingLotFindable;
    }

    public Ticket park(Car car) {
        ParkingLot parkingLot = parkingLotFindable.find(parkingLots);
        return parkingLot.park(car);
    }

    public Car pickUp(Ticket ticket) {
        Optional<ParkingLot> first = parkingLots.stream().filter(lot -> lot.isTicketValid(ticket)).findFirst();
        if(first.isPresent()){
            return first.get().pickUp(ticket);
        }
        throw new TicketIsInvalidException();
    }

    public int getAvailableLots() {
        return parkingLots.stream().mapToInt(x->x.getAvailableLots()).sum();
    }

    public boolean isTicketValid(Ticket ticket) {
        return parkingLots.stream().anyMatch(x->x.isTicketValid(ticket));
    }
}

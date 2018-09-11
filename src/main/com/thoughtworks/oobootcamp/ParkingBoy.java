package com.thoughtworks.oobootcamp;

import com.thoughtworks.oobootcamp.exception.TicketIsInvalidException;
import com.thoughtworks.oobootcamp.findable.ParkingLotFindable;

import java.util.List;
import java.util.Optional;

public class ParkingBoy implements Parkable{
    private final ParkingLotFindable parkingLotFindable;
    private List<ParkingLot> parkables;

    public ParkingBoy(List<ParkingLot> parkables, ParkingLotFindable parkingLotFindable) {
        this.parkables = parkables;
        this.parkingLotFindable = parkingLotFindable;
    }

    @Override
    public Ticket park(Car car) {
        Parkable parkable = parkingLotFindable.find(parkables);
        return parkable.park(car);
    }

    @Override
    public Car pickUp(Ticket ticket) {
        Optional<ParkingLot> first = parkables.stream().filter(lot -> lot.isTicketValid(ticket)).findFirst();
        if(first.isPresent()){
            return first.get().pickUp(ticket);
        }
        throw new TicketIsInvalidException();
    }

    @Override
    public int getAvailableLots() {
        return parkables.stream().mapToInt(x->x.getAvailableLots()).sum();
    }

    @Override
    public boolean isTicketValid(Ticket ticket) {
        return parkables.stream().anyMatch(x->x.isTicketValid(ticket));
    }
}

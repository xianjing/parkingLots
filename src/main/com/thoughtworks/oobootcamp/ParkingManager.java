package com.thoughtworks.oobootcamp;

import com.thoughtworks.oobootcamp.exception.ParkingLotIsFullException;
import com.thoughtworks.oobootcamp.exception.TicketIsInvalidException;

import java.util.List;
import java.util.Optional;

public class ParkingManager {
    private List<Parkable> parkables;

    public ParkingManager(List<Parkable> parkables) {
        this.parkables = parkables;
    }

    public Ticket park(Car car) {
        Optional<Parkable> parkingBoyOptional = parkables.stream().filter(parkingBoy -> parkingBoy.getAvailableLots() > 0).findFirst();
        if (parkingBoyOptional.isPresent()) {
            return parkingBoyOptional.get().park(car);
        }
        throw new ParkingLotIsFullException();
    }

    public Car pickUp(Ticket ticket) {
        Optional<Parkable> parkingBoyOptional = parkables.stream().filter(parkingBoy -> parkingBoy.isTicketValid(ticket)).findFirst();
        if(parkingBoyOptional.isPresent()){
            return parkingBoyOptional.get().pickUp(ticket);
        }
        throw new TicketIsInvalidException();
    }
}

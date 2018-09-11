package com.thoughtworks.oobootcamp;

import com.thoughtworks.oobootcamp.exception.ParkingLotIsFullException;
import com.thoughtworks.oobootcamp.exception.TicketIsInvalidException;

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
        Optional<ParkingBoy> parkingBoyOptional = parkingBoys.stream().filter(parkingBoy -> parkingBoy.getAvailableLots() > 0).findFirst();
        if (parkingBoyOptional.isPresent()) {
            return parkingBoyOptional.get().park(car);
        }

        Optional<ParkingLot> parkingLotOptional = parkingLots.stream().filter(parkingLot -> parkingLot.getAvailableLots() > 0).findFirst();
        if(parkingLotOptional.isPresent()){
            return parkingLotOptional.get().park(car);
        }

        throw new ParkingLotIsFullException();
    }

    public Car pickUp(Ticket ticket) {
        Optional<ParkingBoy> parkingBoyOptional = parkingBoys.stream().filter(parkingBoy -> parkingBoy.isTicketValid(ticket)).findFirst();
        if(parkingBoyOptional.isPresent()){
            return parkingBoyOptional.get().pickUp(ticket);
        }

        Optional<ParkingLot> parkingLotOptional = parkingLots.stream().filter(parkingLot -> parkingLot.isTicketValid(ticket)).findFirst();
        if(parkingLotOptional.isPresent()){
            return parkingLotOptional.get().pickUp(ticket);
        }
        throw new TicketIsInvalidException();
    }
}

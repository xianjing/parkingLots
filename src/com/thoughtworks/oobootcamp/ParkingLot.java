package com.thoughtworks.oobootcamp;

import com.thoughtworks.oobootcamp.exception.ParkingLotIsFullException;
import com.thoughtworks.oobootcamp.exception.TicketIsInvalidException;

import java.util.HashMap;
import java.util.UUID;

public class ParkingLot {
    private int numberOfLots;
    private HashMap<Ticket,Car> carTickets = new HashMap<>();

    public ParkingLot(int numberOfLots) {
        this.numberOfLots = numberOfLots;
    }

    public Ticket park(Car car) {
        boolean hasAvailableLots = getAvailableLots() > 0;
        if(hasAvailableLots){
            Ticket ticket = new Ticket(UUID.randomUUID());
            carTickets.put(ticket,car);
            return ticket;
        }
        throw new ParkingLotIsFullException();
    }

    public Car pickUp(Ticket ticket) {
        if(isTicketValid(ticket)){
            Car car = carTickets.get(ticket);
            carTickets.remove(ticket);
            return car;
        }
        throw new TicketIsInvalidException();
    }

    public int getAvailableLots() {
        return numberOfLots - carTickets.size();
    }

    public boolean isTicketValid(Ticket ticket) {
        return carTickets.containsKey(ticket);
    }
}

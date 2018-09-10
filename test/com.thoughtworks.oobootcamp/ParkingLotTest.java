package com.thoughtworks.oobootcamp;

import com.thoughtworks.oobootcamp.exception.ParkingLotIsFullException;
import com.thoughtworks.oobootcamp.Ticket;
import com.thoughtworks.oobootcamp.exception.TicketIsInvalidException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ParkingLotTest {

    @Test
    public void should_park_succeed_given_available_lots(){
        //given
        ParkingLot parkingLot = new ParkingLot(1);

        //when
        Ticket ticket = parkingLot.park(new Car());

        //then
        assertNotNull(ticket);
    }

    @Test
    public void should_failed_given_lots_is_full(){
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car expectedCar = new Car();
        Ticket ticket = parkingLot.park(expectedCar);

        assertThrows(ParkingLotIsFullException.class, ()-> parkingLot.park(new Car()));
    }

    @Test
    public void should_pickup_right_car_given_a_ticket(){
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car expectedCar = new Car();
        Ticket ticket = parkingLot.park(expectedCar);

        //when pickup
        Car actualCar = parkingLot.pickUp(ticket);

        //then
        assertEquals(expectedCar,actualCar);
    }

    @Test
    public void should_failed_given_an_invalid_ticket(){
        assertThrows(TicketIsInvalidException.class, () -> {
            ParkingLot parkingLot = new ParkingLot(1);
            parkingLot.pickUp(new Ticket(UUID.randomUUID()));
        });
    }

    @Test
    public void should_return_available_lots(){
        ParkingLot parkingLot = new ParkingLot(10);
        parkingLot.park(new Car());

        int availableLots = parkingLot.getAvailableLots();
        assertEquals(9, availableLots);
    }

    @Test
    public void should_return_vailable_lots(){
        ParkingLot parkingLot = new ParkingLot(10);
        parkingLot.pickUp(parkingLot.park(new Car()));
        int availableLots = parkingLot.getAvailableLots();
        assertEquals(10, availableLots);

    }


}

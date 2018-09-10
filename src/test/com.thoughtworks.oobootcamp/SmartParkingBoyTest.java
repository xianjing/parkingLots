package com.thoughtworks.oobootcamp;

import com.thoughtworks.oobootcamp.exception.ParkingLotIsFullException;
import com.thoughtworks.oobootcamp.exception.TicketIsInvalidException;
import com.thoughtworks.oobootcamp.findable.MaxAvailableParkingLotFinder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmartParkingBoyTest {

    @Test
    public void should_park_to_second_lot() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot firstParkingLot = new ParkingLot(1);
        parkingLots.add(firstParkingLot);
        ParkingLot secondParkingLot = new ParkingLot(10);
        parkingLots.add(secondParkingLot);

        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots, new MaxAvailableParkingLotFinder());

        Car expectedCar = new Car();
        Ticket ticket = parkingBoy.park(expectedCar);

        assertEquals(1, firstParkingLot.getAvailableLots());
        assertEquals(9, secondParkingLot.getAvailableLots());
        Car actualCar = secondParkingLot.pickUp(ticket);
        assertSame(expectedCar, actualCar);
    }

    @Test
    public void should_park_failed() {
        //given full
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot firstParkingLot = new ParkingLot(1);
        parkingLots.add(firstParkingLot);
        ParkingLot secondParkingLot = new ParkingLot(1);
        parkingLots.add(secondParkingLot);

        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots, new MaxAvailableParkingLotFinder());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());

        //when
        assertThrows(ParkingLotIsFullException.class, ()->parkingBoy.park(new Car()));
    }

    @Test
    public void should_pickup_right_car_given_right_ticket(){
        //given full
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot firstParkingLot = new ParkingLot(1);
        parkingLots.add(firstParkingLot);
        ParkingLot secondParkingLot = new ParkingLot(10);
        parkingLots.add(secondParkingLot);

        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots, new MaxAvailableParkingLotFinder());
        Car expectedCar = new Car();
        Ticket ticket = parkingBoy.park(expectedCar);

        //when
        Car actualCar = parkingBoy.pickUp(ticket);

        //then
        assertSame(expectedCar,actualCar);
        assertEquals(1, firstParkingLot.getAvailableLots());
        assertEquals(10, secondParkingLot.getAvailableLots());
    }

    @Test
    public void should_pickup_failed_given_invalid_ticket(){
        //given full
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot firstParkingLot = new ParkingLot(1);
        parkingLots.add(firstParkingLot);
        ParkingLot secondParkingLot = new ParkingLot(1);
        parkingLots.add(secondParkingLot);

        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots, new MaxAvailableParkingLotFinder());

        //when
        assertThrows(TicketIsInvalidException.class,() -> parkingBoy.pickUp(new Ticket(UUID.randomUUID())));

    }
}

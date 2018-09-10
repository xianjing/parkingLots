package com.thoughtworks.oobootcamp;

import com.thoughtworks.oobootcamp.exception.ParkingLotIsFullException;
import com.thoughtworks.oobootcamp.exception.TicketIsInvalidException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingBoyTest {
    @Test
    public void should_park_into_first_lot_given_lots_is_available(){
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot firstParkingLot = new ParkingLot(10);
        parkingLots.add(firstParkingLot);
        ParkingLot secondParkingLot = new ParkingLot(10);
        parkingLots.add(secondParkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        Car expectedCar = new Car();
        Ticket ticket = parkingBoy.park(expectedCar);

        Car actualCar = firstParkingLot.pickUp(ticket);

        assertSame(expectedCar,actualCar);
    }

    @Test
    public void should_park_into_second_lot_given_first_is_full(){
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot firstParkingLot = new ParkingLot(0);
        parkingLots.add(firstParkingLot);
        ParkingLot secondParkingLot = new ParkingLot(10);
        parkingLots.add(secondParkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        Car expectedCar = new Car();
        Ticket ticket = parkingBoy.park(expectedCar);

        Car actualCar = secondParkingLot.pickUp(ticket);

        assertSame(expectedCar,actualCar);
    }

    @Test
    public void should_failed_when_all_lots_are_full(){
        //given full
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot firstParkingLot = new ParkingLot(1);
        parkingLots.add(firstParkingLot);
        ParkingLot secondParkingLot = new ParkingLot(1);
        parkingLots.add(secondParkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
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
        ParkingLot secondParkingLot = new ParkingLot(1);
        parkingLots.add(secondParkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car expectedCar = new Car();
        Ticket ticket = parkingBoy.park(expectedCar);

        //when
        Car actualCar = parkingBoy.pickUp(ticket);

        //then
        assertSame(expectedCar,actualCar);
    }

    @Test
    public void should_pickup_failed_given_invalid_ticket(){
        //given full
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot firstParkingLot = new ParkingLot(1);
        parkingLots.add(firstParkingLot);
        ParkingLot secondParkingLot = new ParkingLot(1);
        parkingLots.add(secondParkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        assertThrows(TicketIsInvalidException.class,() -> parkingBoy.pickUp(new Ticket(UUID.randomUUID())));

    }

}

package com.thoughtworks.oobootcamp;

import com.thoughtworks.oobootcamp.findable.OrderedParkingLotFinder;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class ParkingManagerTest {

    @Test
    public void should_park_success(){

        ParkingLot parkingBoyFirstLot = new ParkingLot(1);
        List<ParkingLot> parkingBoyLots = Arrays.asList(parkingBoyFirstLot);
        List<ParkingBoy> parkingBoys = Arrays.asList(new ParkingBoy(parkingBoyLots, new OrderedParkingLotFinder()));
        ParkingLot parkingManagerLot = new ParkingLot(1);
        List<ParkingLot> parkingManagerLots = Arrays.asList(parkingManagerLot);
        ParkingManager parkingManager = new ParkingManager(parkingBoys, parkingManagerLots);
        Ticket ticket = parkingManager.park(new Car());

        assertNotNull(ticket);
        assertEquals(0, parkingBoyFirstLot.getAvailableLots());

    }

    @Test
    public void should_pickup_success(){

        ParkingLot parkingBoyFirstLot = new ParkingLot(1);
        List<ParkingLot> parkingBoyLots = Arrays.asList(parkingBoyFirstLot);
        List<ParkingBoy> parkingBoys = Arrays.asList(new ParkingBoy(parkingBoyLots, new OrderedParkingLotFinder()));
        ParkingLot parkingManagerLot = new ParkingLot(1);
        List<ParkingLot> parkingManagerLots = Arrays.asList(parkingManagerLot);
        ParkingManager parkingManager = new ParkingManager(parkingBoys, parkingManagerLots);
        Car expectedCar = new Car();
        Ticket ticket = parkingManager.park(expectedCar);

        Car actualCar = parkingManager.pickUp(ticket);

        assertSame(expectedCar, actualCar);
        assertEquals(1, parkingBoyFirstLot.getAvailableLots());

    }


}

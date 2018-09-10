package com.thoughtworks.oobootcamp.findable;

import com.thoughtworks.oobootcamp.ParkingLot;
import com.thoughtworks.oobootcamp.exception.ParkingLotIsFullException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MaxVacancyRateParkingLotFinder implements ParkingLotFindable {
    @Override
    public ParkingLot find(List<ParkingLot> parkingLots) {
        Optional<ParkingLot> first = parkingLots.stream()
                .filter(lot -> lot.getAvailableLots() > 0)
                .sorted(Comparator.comparing(ParkingLot::getVacancyRate).reversed())
                .findFirst();
        if (!first.isPresent()) {
            throw new ParkingLotIsFullException();
        }
        return first.get();
    }
}

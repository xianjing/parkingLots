package com.thoughtworks.oobootcamp.findable;

import com.thoughtworks.oobootcamp.Parkable;
import com.thoughtworks.oobootcamp.ParkingLot;
import com.thoughtworks.oobootcamp.exception.ParkingLotIsFullException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MaxVacancyRateParkingLotFinder implements ParkingLotFindable {

    @Override
    public Parkable find(List<ParkingLot> parkables) {
        Optional<ParkingLot> first = parkables.stream()
                .filter(lot -> lot.getAvailableLots() > 0)
                .max(Comparator.comparing(t -> t.getVacancyRate()));
        if (!first.isPresent()) {
            throw new ParkingLotIsFullException();
        }
        return first.get();
    }
}

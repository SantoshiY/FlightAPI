package com.FlightAPI.Service;

import com.FlightAPI.Payload.ReservationDTO;

public interface ReservationService {
    ReservationDTO saveReser(long flightId, long passengerId, ReservationDTO reservationDto);}

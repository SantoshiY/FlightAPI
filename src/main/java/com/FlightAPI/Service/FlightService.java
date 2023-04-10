package com.FlightAPI.Service;

import com.FlightAPI.Payload.FlightDTO;
import com.FlightAPI.Payload.FlightResponse;

import java.util.Date;
import java.util.List;

public interface FlightService {
    public FlightDTO saveflight(FlightDTO flightDTO);

    FlightDTO getFlightById(long fid);

    FlightDTO updateFlight(long fid, FlightDTO flightDTO);

    void deleteflightById(long fid);

    FlightResponse gellAllFlight(int pageNo, int pageSize, String sortBy, String sortDir);

    List<FlightDTO> findFlights(String from, String to);
            //, Date departureDate);

}

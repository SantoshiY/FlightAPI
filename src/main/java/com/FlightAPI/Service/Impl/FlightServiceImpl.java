package com.FlightAPI.Service.Impl;

import com.FlightAPI.Exception.ResourceNotFoundException;
import com.FlightAPI.Payload.FlightDTO;
import com.FlightAPI.Payload.FlightResponse;
import com.FlightAPI.Repository.FlightRepo;
import com.FlightAPI.Service.FlightService;
import com.FlightAPI.entity.Flight;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepo flightRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public FlightDTO saveflight(FlightDTO flightDTO) {
        Flight flight = mapToEntity(flightDTO);
        Flight save = flightRepo.save(flight);

        return mapToDto(save);
    }

    @Override
    public FlightDTO getFlightById(long fid) {
        Flight flight = flightRepo.findById(fid).orElseThrow(()->new ResourceNotFoundException("flight","id",+fid));
        return mapToDto(flight);
    }

    @Override
    public FlightDTO updateFlight(long fid, FlightDTO flightDTO) {
        Flight flight = flightRepo.findById(fid).orElseThrow(()->new ResourceNotFoundException("flight","id",+fid));

        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setArrivalCity(flightDTO.getArrivalCity());
        flight.setDateOfDeparture(flightDTO.getDateOfDeparture());
        flight.setDepartureCity(flightDTO.getDepartureCity());
        flight.setEstimatedDepartureTime(flightDTO.getEstimatedDepartureTime());
        flight.setOperatingAirlines(flightDTO.getOperatingAirlines());

        Flight save = flightRepo.save(flight);
        return mapToDto(save);
    }

    @Override
    public void deleteflightById(long fid) {
        flightRepo.deleteById(fid);
    }

    @Override
    public FlightResponse gellAllFlight(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending(); //here we use ternary oprators it will work as a if else
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);//there is inbuild method sort with the filter by which is help me to stored the string data
        Page<Flight> f = flightRepo.findAll(pageable);
        List<Flight> flight = f.getContent();
        List<FlightDTO> dto = flight.stream().map(ft -> mapToDto(ft)).collect(Collectors.toList());

        FlightResponse flightResponse = new FlightResponse();

        flightResponse.setContent(dto);
        flightResponse.setPageNo(f.getNumber());
        flightResponse.setPageSize(f.getSize());
        flightResponse.setTotalElements(f.getTotalElements());
        flightResponse.setTotalPage(f.getTotalPages());
        flightResponse.setLastPage(f.isLast());
        return flightResponse;
    }

    @Override
    public List<FlightDTO> findFlights(String from, String to){
            //, Date departureDate) {
        List<Flight> flights = flightRepo.findFlights(from, to);

        return flights.stream().map(ft -> mapToDto(ft)).collect(Collectors.toList());
    }



    public FlightDTO mapToDto(Flight flight){
        FlightDTO flightDTO = modelMapper.map(flight, FlightDTO.class);
        return flightDTO;
    }

    public Flight mapToEntity(FlightDTO flightDTO){
        Flight flight = modelMapper.map(flightDTO, Flight.class);
        return flight;
    }
}

package com.FlightAPI.Service.Impl;


import com.FlightAPI.Payload.ReservationDTO;
import com.FlightAPI.Repository.FlightRepo;
import com.FlightAPI.Repository.PassengerRepo;
import com.FlightAPI.Repository.ReservationRepo;
import com.FlightAPI.Service.ReservationService;
import com.FlightAPI.entity.Flight;
import com.FlightAPI.entity.Passenger;
import com.FlightAPI.entity.Reservation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private FlightRepo flightRepo;
    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private PassengerRepo passengerRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ReservationDTO saveReser(long flightId, long passengerId, ReservationDTO reservationDto) {

        Reservation reservation = mapToEntity(reservationDto);
        Flight flight = flightRepo.findById(flightId).orElseThrow(
                () -> new EntityNotFoundException("flight not found with flight id " + flightId)
        );

        Passenger passenger = passengerRepo.findById(passengerId).orElseThrow(
                () -> new EntityNotFoundException("Passenger not fount with passengerId " + passengerId)
        );
        reservation.setFlight(flight);
        reservation.setPassenger(passenger);

        Reservation newReservation = reservationRepo.save(reservation);
        // reservationDto.setFlight(newReservation.getFlight());
        // reservationDto.setPassenger(newReservation.getPassenger());


        return mapToDto(newReservation);
    }

    private Reservation mapToEntity(ReservationDTO reservationDto){
        Reservation reservation= modelMapper.map(reservationDto , Reservation.class);
        return reservation;
    }

    private  ReservationDTO mapToDto(Reservation reservation){
        ReservationDTO dto = modelMapper.map(reservation, ReservationDTO.class);
        return dto;
    }
}
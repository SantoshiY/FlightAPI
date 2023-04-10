package com.FlightAPI.Service.Impl;

import com.FlightAPI.Payload.PassengerDTO;
import com.FlightAPI.Repository.PassengerRepo;
import com.FlightAPI.Service.PassengerService;
import com.FlightAPI.entity.Passenger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerServiceImpl implements PassengerService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private PassengerRepo passengerRepo;
    @Override
    public PassengerDTO savePassenger(PassengerDTO passengerDTO) {
        Passenger passenger = mapToEntity(passengerDTO);
        Passenger passenger1 = passengerRepo.save(passenger);
        return mapToDto(passenger1);
    }

    PassengerDTO mapToDto(Passenger passenger){
        PassengerDTO dto = modelMapper.map(passenger, PassengerDTO.class);
        return dto;
    }

    Passenger mapToEntity(PassengerDTO passengerDTO){
        Passenger passenger = modelMapper.map(passengerDTO, Passenger.class);
        return passenger;
    }
}

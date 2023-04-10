package com.FlightAPI.Controller;

import com.FlightAPI.Payload.PassengerDTO;
import com.FlightAPI.Service.PassengerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PassengerController {
    @Autowired
    private PassengerService passengerService;

    //http://localhost:8080/flights/api/savepassenger
    @PostMapping("/savepassenger")
    public ResponseEntity<PassengerDTO> savePassenger(@RequestBody PassengerDTO passengerDTO){
        PassengerDTO dto = passengerService.savePassenger(passengerDTO);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}

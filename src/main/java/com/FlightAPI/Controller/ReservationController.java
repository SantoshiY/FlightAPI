package com.FlightAPI.Controller;

import com.FlightAPI.Payload.ReservationDTO;
import com.FlightAPI.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/res")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;


    //http://localhost:8080/flights/api/res/flightId/1/passenger/1/reservation
    @PostMapping("/flightId/{flightId}/passenger/{passengerId}/reservation")
    public ResponseEntity<ReservationDTO> saveReservation(@PathVariable("flightId") long flightId, @PathVariable("passengerId") long passengerId,
                                                          @RequestBody ReservationDTO reservationDto){
        ReservationDTO dto = reservationService.saveReser(flightId ,passengerId, reservationDto);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
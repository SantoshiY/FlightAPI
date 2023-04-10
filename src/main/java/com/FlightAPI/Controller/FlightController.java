package com.FlightAPI.Controller;

import com.FlightAPI.Payload.FlightDTO;
import com.FlightAPI.Payload.FlightResponse;
import com.FlightAPI.Service.FlightService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FlightController {

    @Autowired
    private FlightService flightService;

    //http://localhost:8080/flights/api/saveflight
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveflight")
    public ResponseEntity<Object> saveFlight(@Valid @RequestBody FlightDTO flightDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        FlightDTO saveflights = flightService.saveflight(flightDTO);
        return new ResponseEntity<>(saveflights, HttpStatus.CREATED);
    }

    //http://localhost:8080/flights/api/{fid}/getflight
    @GetMapping("/{fid}/getflight")
    public ResponseEntity<FlightDTO> getFlightById(@PathVariable("fid") long fid){
        FlightDTO dto = flightService.getFlightById(fid);
        return ResponseEntity.ok(dto);
    }

    //http://localhost:8080/flights/api/getallflight
    //http://localhost:8080/flights/api/getallflight?pageNo=0&pageSize=5&sortBy=flightId&sortDir=asc     (for pagination and sorting as well as asc or desc)

    @GetMapping("/getallflight")
    public FlightResponse getAllFlight(@RequestParam(value="pageNo", defaultValue = "0", required = false) int pageNo,
                                       @RequestParam(value="pageSize", defaultValue = "10", required = false) int pageSize,
                                       @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                       @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir){
        return flightService.gellAllFlight(pageNo,pageSize,sortBy,sortDir);
    }

    //http://localhost:8080/flights/api/{fid}/updateflight
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{fid}/updateflight")
    public ResponseEntity<Object> updateFlight(@PathVariable("fid") long fid, @Valid @RequestBody FlightDTO flightDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        FlightDTO dto = flightService.updateFlight(fid, flightDTO);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //http://localhost:8080/flights/api/{fid}/deleteflight
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{fid}/deleteflight")
    public ResponseEntity<String> deleteflightById(@PathVariable("fid") long fid){
        flightService.deleteflightById(fid);
        return new ResponseEntity<>("Flight is deleted successfully "+fid, HttpStatus.OK);
    }

    //http://localhost:8080/flights/api/search?from&to&departureDate
    @GetMapping("/search")
    public List<FlightDTO> findFlights(@RequestParam("from")String from, @RequestParam("to") String to){
            //, @RequestParam("departureDate") @DateTimeFormat(pattern = "MM-dd-yyyy") Date departureDate){
   // public List<FlightDTO> findFlights(@RequestParam("from")String from, @RequestParam("to") String to){

        return flightService.findFlights(from, to);
    }


}

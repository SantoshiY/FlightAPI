package com.FlightAPI.Repository;

import com.FlightAPI.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface FlightRepo extends JpaRepository<Flight, Long> {
    //public List<Flight> searchFlight(String departureCity, String arrivalCity, Date dateOfDeparture);

    @Query("from Flight where departureCity=:departureCity and arrivalCity=:arrivalCity")
            // and dateOfDeparture=:dateOfDeparture")
    List<Flight> findFlights(@Param("departureCity") String from, @Param("arrivalCity") String to);
            //, @Param("departureDate") Date departureDate);

}


//    @Query("SELECT f FROM Flight f WHERE f.departureCity = :departureCity AND f.arrivalCity = :arrivalCity AND f.dateOfDeparture = :dateOfDeparture")
//    List<Flight> searchFlights(@Param("departureCity") String departureCity, @Param("arrivalCity") String arrivalCity, @Param("dateOfDeparture") Date dateOfDeparture);


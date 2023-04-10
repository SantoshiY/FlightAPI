package com.FlightAPI.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDTO {
        private long flightId;
        private String flightNumber;
        // operatingAirlines should not be null or empty
        // operatingAirlines should have at least 2 characters
        @NotEmpty
        @Size(min = 2, message = "operatingAirlines should have at least 2 characters")
        private String operatingAirlines;
        @NotEmpty
        @Size(min = 2, message = "departureCity should have at least 2 characters")
        private String departureCity;
        @NotEmpty
        @Size(min = 2, message = "arrivalCity should have at least 2 characters")
        private String arrivalCity;
        private Date dateOfDeparture;
        private Timestamp estimatedDepartureTime;
    }



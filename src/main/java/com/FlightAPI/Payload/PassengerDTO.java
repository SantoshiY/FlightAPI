package com.FlightAPI.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDTO {
    private long passengerId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String phone;
}

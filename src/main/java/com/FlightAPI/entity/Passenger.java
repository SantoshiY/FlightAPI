package com.FlightAPI.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "passengers")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long passengerId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String phone;
//    @OneToOne(mappedBy = "passenger", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Flight flight;

}

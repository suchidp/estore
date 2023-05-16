package com.estore.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;


import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;
    private String street;
    private int zipCode;
    private String city;
    private String state;


    @ManyToOne
    @JoinColumn(name = "user_id")
//    @JsonIgnore
    private User user;


    @Column(name="date_created")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime dateCreated;



    @Column(name="last_updated")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime lastUpdated; ;
}

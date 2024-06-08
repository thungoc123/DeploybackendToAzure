package com.example.SWP391.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "location")
    private String location;

    @Column(name = "timeticketopen")
    private LocalDateTime timeticketopen;
    @Column(name = "timeticketclosed")
    private LocalDateTime timeticketclosed;
    @Column(name = "timeeventstart")
    private LocalDateTime timeeventstart;
    @Column(name = "timeeventend")
    private LocalDateTime timeeventend;
}

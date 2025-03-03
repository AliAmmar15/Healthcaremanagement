package com.healthcare.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime appointmentDate;
    private String reason;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private User doctor;

    // Getters and Setters
}
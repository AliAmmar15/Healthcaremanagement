package com.healthcare.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate issueDate;
    private String medication;
    private String dosage;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private User doctor;

    // Getters and Setters
}
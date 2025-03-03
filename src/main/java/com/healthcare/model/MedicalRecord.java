package com.healthcare.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate recordDate;
    private String description;

    @ManyToOne
    private Patient patient;

    // Getters and Setters
}
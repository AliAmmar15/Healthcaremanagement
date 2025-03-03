package com.healthcare.service;

import com.healthcare.model.Appointment;
import com.healthcare.repository.AppointmentRepository;
import com.healthcare.exception.AppointmentNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.time.LocalDateTime;

/**
 * Service class for managing appointments.
 */
@Service
@Transactional
public class AppointmentService {
    
    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    /**
     * Creates a new appointment.
     *
     * @param appointment the appointment to create
     * @return the saved appointment
     */
    public Appointment saveAppointment(Appointment appointment) {
        Assert.notNull(appointment, "Appointment cannot be null");
        validateAppointment(appointment);
        return appointmentRepository.save(appointment);
    }

    /**
     * Retrieves an appointment by its id.
     *
     * @param id the appointment id
     * @return the appointment
     * @throws AppointmentNotFoundException if appointment is not found
     */
    @Transactional(readOnly = true)
    public Appointment getAppointmentById(Long id) {
        Assert.notNull(id, "Id cannot be null");
        return appointmentRepository.findById(id)
            .orElseThrow(() -> new AppointmentNotFoundException(id));
    }

    /**
     * Retrieves all appointments.
     *
     * @return list of all appointments
     */
    @Transactional(readOnly = true)
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    /**
     * Updates an existing appointment.
     *
     * @param id the appointment id
     * @param appointment the updated appointment details
     * @return the updated appointment
     * @throws AppointmentNotFoundException if appointment is not found
     */
    public Appointment updateAppointment(Long id, Appointment appointment) {
        Assert.notNull(id, "Id cannot be null");
        Assert.notNull(appointment, "Appointment cannot be null");
        
        getAppointmentById(id); // Check if exists
        validateAppointment(appointment);
        appointment.setId(id);
        return appointmentRepository.save(appointment);
    }

    /**
     * Deletes an appointment.
     *
     * @param id the appointment id
     * @throws AppointmentNotFoundException if appointment is not found
     */
    public void deleteAppointment(Long id) {
        Assert.notNull(id, "Id cannot be null");
        getAppointmentById(id); // Check if exists
        appointmentRepository.deleteById(id);
    }

    private void validateAppointment(Appointment appointment) {
        Assert.notNull(appointment.getPatientId(), "Patient ID cannot be null");
        Assert.notNull(appointment.getDoctorId(), "Doctor ID cannot be null");
        Assert.notNull(appointment.getAppointmentTime(), "Appointment time cannot be null");
        
        if (appointment.getAppointmentTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Appointment time cannot be in the past");
        }
    }
}
package com.user_interface.user_interface.repository;

import com.user_interface.user_interface.entity.Doctors_schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DoctorScheduleRepository extends JpaRepository<Doctors_schedule,Long> {

    @Query(value = "SELECT * FROM doctor_schedule WHERE doctor_id = ?1 and position_in_queue = ?2",nativeQuery = true)
    Doctors_schedule getByDoctor_idAndPosition_in_queue(int doctor_id, int position_in_queue);
    
    @Query(value = "Update doctor_schedule SET position_in_queue = position_in_queue+1 WHERE doctor_id = ?1",nativeQuery=true)
    void updatePositionsPlusOne(int doctor_id);
    @Query(value = "SELECT * FROM doctor_schedule where doctor_id = ?1",nativeQuery = true)
    List<Doctors_schedule> findDoctors_schedules(int doctor_id);
    @Query(value = "SELECT * FROM doctor_schedule where patient_id = ?1",nativeQuery = true)
    Optional<Doctors_schedule> findByPatient_id(int patient_id);

}

package com.user_interface.user_interface.repository;

import com.user_interface.user_interface.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    Optional<Doctor> findDoctorById(int id);
    @Query(value = "SELECT * FROM doctors where user_id = ?1",nativeQuery = true)
    Doctor findByUser_id(long id);
    List<Doctor> findAllBySpeciality(int speciality);

}

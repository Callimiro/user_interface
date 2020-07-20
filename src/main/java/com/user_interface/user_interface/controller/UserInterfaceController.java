package com.user_interface.user_interface.controller;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import com.user_interface.user_interface.entity.Doctor;
import com.user_interface.user_interface.entity.Doctors_schedule;
import com.user_interface.user_interface.entity.User;
import com.user_interface.user_interface.repository.DoctorRepository;
import com.user_interface.user_interface.repository.DoctorScheduleRepository;
import com.user_interface.user_interface.response.PatientDoctorPosition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.user_interface.user_interface.repository.UserRepos;
import com.user_interface.user_interface.service.DoctorService;

import java.util.List;

@RestController
public class UserInterfaceController {
    Logger logger = LoggerFactory.getLogger(UserInterfaceController.class);

    @Autowired
    UserRepos userRepos;
    @Autowired
    DoctorService doctorService;
    @Autowired
    DoctorScheduleRepository doctorScheduleRepository;
    @Autowired
    DoctorRepository doctorRepository;

    @PostMapping(value = "/waitinglist")
    public List getDoctorList(@RequestBody String name){

        return doctorService.getDoctorsSchedule(name);
    }

    @GetMapping(value = "/patientposition/{id}")
    public ResponseEntity<PatientDoctorPosition> getPatientPosition(@PathVariable("id") int patietnId){
        logger.info(String.valueOf(patietnId));
        Doctors_schedule doctors_schedule = doctorScheduleRepository.findByPatient_id(patietnId)
                .orElseThrow(() -> new RuntimeException("no schedule found"));
        logger.info(doctors_schedule.toString());
        Doctor doctor = doctorRepository.findDoctorById(doctors_schedule.getDoctor_id())
                .orElseThrow(() -> new RuntimeException("doctor not found"));
        logger.info(doctor.toString());
        long doctorUserId = doctor.getUser_id();
        User user = userRepos.findById(doctorUserId)
                .orElseThrow(() -> new RuntimeException("user not found"));
        logger.info(user.toString());
        PatientDoctorPosition patientDoctorPosition = new PatientDoctorPosition();
        patientDoctorPosition.setDoctorName(user.getUsername());
        patientDoctorPosition.setPosition(doctors_schedule.getPosition_in_queue());
        return ResponseEntity.ok(patientDoctorPosition);
    }
}

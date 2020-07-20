package com.user_interface.user_interface.service;

import com.user_interface.user_interface.entity.Doctor;
import com.user_interface.user_interface.entity.Doctors_schedule;
import com.user_interface.user_interface.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.user_interface.user_interface.repository.DoctorRepository;
import com.user_interface.user_interface.repository.DoctorScheduleRepository;
import com.user_interface.user_interface.repository.UserRepos;

import java.util.List;


@Service
public class DoctorService {
    @Autowired
    UserRepos userRepos;
    @Autowired
    DoctorScheduleRepository doctorScheduleRepository;
    @Autowired
    DoctorRepository doctorRepository;

    public List<Doctors_schedule> getDoctorsSchedule(String username){
        User user= userRepos.getByUsername(username);
        long userid=user.getId();
        Doctor doctor=doctorRepository.findByUser_id(userid);
        return doctorScheduleRepository.findDoctors_schedules(doctor.getId());
    }
}

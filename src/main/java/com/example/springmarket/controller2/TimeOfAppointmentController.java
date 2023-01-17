package com.example.springmarket.controller2;


import com.example.springmarket.model.Doctor;
import com.example.springmarket.model.TimeOfAppointment;
import com.example.springmarket.repository.DoctorRepository;
import com.example.springmarket.repository.TimeOfAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/timeOfAppointment")
public class TimeOfAppointmentController {

    @Autowired
    private TimeOfAppointmentRepository timeOfAppointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping("/{DoctorId}")
    public String getTimeOfAppointmentById(@PathVariable("DoctorId") int DoctorId, Model model) {
        Doctor doctor = doctorRepository.getById(DoctorId);
        model.addAttribute("mess", "Запис до доктора: " + doctor.getName() + " " + doctor.getSurname());
        List<TimeOfAppointment> timeOfAppointmentList = doctor.getTimeOfAppointments();
        model.addAttribute("timeOfAppointmentList", timeOfAppointmentList);
        model.addAttribute("doctorId", DoctorId);
        return "ShowTimeOfAppointments";
    }

    @GetMapping("/update/{id}")
    public String updateTimeOfAppointmentById(@PathVariable(name = "id") int id, Model model) {
        TimeOfAppointment timeOfAppointment = timeOfAppointmentRepository.getById(id);

        int doctorId = timeOfAppointment.getDoctor().getId();
        model.addAttribute("id", id);
        model.addAttribute("doctorId", doctorId);
        model.addAttribute("timeOfAppointment", timeOfAppointment);
        return "UpdateTimeOfAppointmentForm";
    }

    @GetMapping("/add/{doctorId}")
    public String addTimeOfAppointmentById(@PathVariable(name = "doctorId") int doctorId, Model model) {
        model.addAttribute("doctorId", doctorId);
        return "AddTimeOfAppointmentForm";
    }

    @PostMapping("/add")
    public String addTimeOfAppointmentById(@RequestParam(name = "date") String date, @RequestParam(name = "doctorId") int doctorId) {
        Doctor doctor = doctorRepository.getById(doctorId);
        TimeOfAppointment timeOfAppointment = new TimeOfAppointment(date, doctor);
        timeOfAppointmentRepository.save(timeOfAppointment);
        String mess = "redirect:/timeOfAppointment/" + doctorId;
        return mess;
    }

    @PostMapping("/update")
    public String updateTimeOfAppointmentById(@RequestParam(name = "date") String date, @RequestParam(name = "doctorId") int doctorId,
                                              @RequestParam(name = "id") int id) {
        Doctor doctor = doctorRepository.getById(doctorId);
        TimeOfAppointment timeOfAppointment = timeOfAppointmentRepository.getById(id);
        timeOfAppointment.setDate(date);
        timeOfAppointmentRepository.flush();
        String mess = "redirect:/timeOfAppointment/" + doctorId;
        return mess;
    }




    @PostMapping("/delete/{id}")
    public String deleteTimeOfAppointmentById(@PathVariable(name = "id") int id) {
        TimeOfAppointment timeOfAppointment = timeOfAppointmentRepository.findById(id).get();
        int doctorId = timeOfAppointment.getDoctor().getId();
        String mess = "redirect:/timeOfAppointment/" + doctorId;
        timeOfAppointmentRepository.deleteById(id);
        return mess;
    }


}

package com.kyu.springbackend.controllers;

import com.kyu.springbackend.model.Staff;
import com.kyu.springbackend.services.staff.StaffService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staffs")
public class StaffController {

    private final StaffService service;

    public StaffController(StaffService service) {
        this.service = service;
    }

    @GetMapping
    public List<Staff> getAllStaff() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Staff getStaffById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public Staff postNewStaff(@RequestBody Staff newStaff) {
        return service.save(newStaff);
    }

    @PutMapping("/{id}")
    public Staff putNewStaff(@PathVariable String id, @RequestBody Staff newStaff) {
        return service.update(id, newStaff);
    }

    @DeleteMapping("/{id}")
    public void deleteStaff(@PathVariable String id) {
        service.deleteById(id);
    }

}

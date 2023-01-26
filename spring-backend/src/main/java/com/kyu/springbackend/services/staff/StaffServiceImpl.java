package com.kyu.springbackend.services.staff;

import com.kyu.springbackend.model.Staff;
import com.kyu.springbackend.repositories.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StaffServiceImpl implements StaffService{

    private final StaffRepository repository;

    public StaffServiceImpl(StaffRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Staff> findAll() {
        return repository.findAll();
    }

    @Override
    public Staff findById(String s) {
        return repository.findById(s).orElse(null);
    }

    @Override
    public Staff save(Staff object) {
        return repository.save(object);
    }

    @Override
    public Staff update(String id, Staff newStaff) {
        return repository.findById(id)
                .map(staff -> {
                    staff.update(newStaff);
                    return repository.save(staff);
                })
                .orElseGet(() -> {
                    newStaff.setId(id);
                    return repository.save(newStaff);
                });
    }

    @Override
    public void delete(Staff object) {
        repository.delete(object);
    }

    @Override
    public void deleteById(String s) {
        repository.deleteById(s);
    }
}

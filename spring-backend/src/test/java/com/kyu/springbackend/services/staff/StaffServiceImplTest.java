package com.kyu.springbackend.services.staff;

import com.kyu.springbackend.model.Staff;
import com.kyu.springbackend.repositories.StaffRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class StaffServiceImplTest {

    StaffServiceImpl staffService;

    @Mock
    StaffRepository staffRepository;

    @BeforeEach
    void setUp() {
        staffService = new StaffServiceImpl(staffRepository);
    }

    @Test
    void getAllStaff() {
        Staff staff1 = new Staff();
        staff1.setId("1");
        Staff staff2 = new Staff();
        staff2.setId("2");
        List<Staff> staffs = new LinkedList<>();
        staffs.add(staff1);
        staffs.add(staff2);

        when(staffRepository.findAll()).thenReturn(staffs);
        List<Staff> returnedStaffs = staffService.findAll();

        assertEquals(staffs, returnedStaffs);
        verify(staffRepository, times(1)).findAll();
        verify(staffRepository, never()).findById(anyString());

    }

    @Test
    void getStaffByIdTest() {
        Staff staff = new Staff();
        staff.setId("1");
        Optional<Staff> staffOptional = Optional.of(staff);

        when(staffRepository.findById(anyString())).thenReturn(staffOptional);
        Staff staffReturned = staffService.findById("1");

        assertEquals(staffOptional.get(), staffReturned);
        verify(staffRepository, times(1)).findById(anyString());
        verify(staffRepository, never()).findAll();
    }

    @Test
    void saveStaff() {
        Staff staff = new Staff();
        staff.setId("1");

        when(staffRepository.save(any())).thenReturn(staff);
        Staff savedStaff = staffService.save(staff);

        assertEquals(staff, savedStaff);
        verify(staffRepository, times(1)).save(any());
    }

    @Test
    void updateExistingStaff() {
        Staff staff = new Staff();
        staff.setId("1");
        staff.setFirstName("Adam");
        Optional<Staff> staffOptional = Optional.of(staff);

        Staff newUpdateStaff = new Staff();
        newUpdateStaff.setId("1");
        newUpdateStaff.setFirstName("bob");

        when(staffRepository.findById(anyString())).thenReturn(staffOptional);
        when(staffRepository.save(any())).thenReturn(newUpdateStaff);
        Staff returnedStaff = staffService.update(staff.getId(), newUpdateStaff);

        assertEquals(newUpdateStaff, returnedStaff);
        verify(staffRepository, times(1)).findById(anyString());
        verify(staffRepository, times(1)).save(any());
    }

    @Test
    void updateEmptyStaff() {
        Staff newUpdateStaff = new Staff();
        newUpdateStaff.setId("1");
        newUpdateStaff.setFirstName("bob");

        when(staffRepository.findById(anyString())).thenReturn(Optional.empty());
        when(staffRepository.save(any())).thenReturn(newUpdateStaff);
        Staff returnedStaff = staffService.update(newUpdateStaff.getId(), newUpdateStaff);

        assertEquals(newUpdateStaff, returnedStaff);
        verify(staffRepository, times(1)).findById(anyString());
        verify(staffRepository, times(1)).save(any());
    }

    @Test
    void deleteById() {
        Staff staff = new Staff();
        staff.setId("1");
        staffService.deleteById(staff.getId());

        verify(staffRepository, times(1)).deleteById(anyString());
    }
}
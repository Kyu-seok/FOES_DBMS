package com.kyu.springbackend.bootstrap;

import com.kyu.springbackend.model.Asset;
import com.kyu.springbackend.model.Staff;
import com.kyu.springbackend.model.user.Authority;
import com.kyu.springbackend.model.user.User;
import com.kyu.springbackend.repositories.AssetRepository;
import com.kyu.springbackend.repositories.StaffRepository;
import com.kyu.springbackend.repositories.UserDetailsRepository;
import com.kyu.springbackend.services.staff.StaffService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Profile("bootstrap")
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final StaffService staffService;
    private final StaffRepository staffRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;
    private final AssetRepository assetRepository;

    @Override
    public void run(String... args) throws Exception {
        long userCount = userDetailsRepository.count();

        if (userCount == 0) {
            userDetailsRepository.deleteAll();
        }

        createUser();

    }

    private void loadData() {
        Staff staff1 = new Staff();
        staff1.setFirstName("Adam");
        staff1.setLastName("Smith");
        staff1.setTitle("Lecturer");
        staff1.setStaffId("70000001");
        // staff1.setReportDutyDate(new Date());
        staff1.setDepartment("Computing");
        staff1.setPosition("Associate");
        staff1.setRoomNo("room1");
        staff1.setExtNo("ext1");
        staff1.setStatus("status1");
        staff1.setEmail("email@email.com");
        staff1.setAppointmentLevel("level1");
        staff1.setPhotocopyId("ID");
        staff1.setPigeonboxNo("pigeonboxno1");
        // staff1.setResignedDate(new Date());
        staff1.setRemark("no remark is the remark");
        staffRepository.save(staff1);

        Staff staff2 = new Staff();
        staff2.setFirstName("Bob");
        staff2.setLastName("Dan");
        staff2.setTitle("Lecturer");
        staff2.setStaffId("70000002");
        // staff2.setReportDutyDate(new Date());
        staff2.setDepartment("Computing");
        staff2.setPosition("Associate");
        staff2.setRoomNo("room2");
        staff2.setExtNo("ext2");
        staff2.setStatus("status2");
        staff2.setEmail("email@email.com");
        staff1.setAppointmentLevel("level2");
        staff2.setPhotocopyId("ID");
        staff2.setPigeonboxNo("pigeonboxno2");
        // staff2.setResignedDate(new Date());
        staff2.setRemark("no remark is the remark");
        staffRepository.save(staff2);

        System.out.println("Loaded 2 Staffs...");

        Asset asset1 = new Asset();
        asset1.setPhysicalCheck("physical good");
        asset1.setAssetTagNumber("0001");
        asset1.setItem("Chair");
        asset1.setDescription("Chair with wheels");
        asset1.setSerialNo("ch01");
        asset1.setYearPurchased("2023");
        asset1.setWarranty("2025");
        asset1.setQuantity(1);
        asset1.setLocation("Room 1");
        asset1.setOriginalCost("200");
        asset1.setConditionOfAsset("Good");
        asset1.setEndUser("Student");
        asset1.setGrant("0");
        asset1.setBrand("Chair brand");
        asset1.setModelNo("Model 001");
        assetRepository.save(asset1);
        System.out.println("Loaded 1 Asset");
    }

    private void createUser() {
        List<Authority> authorityList1 = new ArrayList<>();
        authorityList1.add(createAuthority("USER", "User role"));

        List<Authority> authorityList2 = new ArrayList<>();
        authorityList2.add(createAuthority("USER", "User role"));
        authorityList2.add(createAuthority("ADMIN", "Admin role"));

        User user0 = new User();
        user0.setUserName("admin@test.com");
        user0.setPassword(passwordEncoder.encode("password"));
        user0.setFirstName("John");
        user0.setLastName("Doe");
        user0.setEmail("john.d@test.com");
        user0.setPhoneNumber("010-1234-5678");
        user0.setEnabled(true);
        user0.setAuthorities(authorityList2);
        userDetailsRepository.save(user0);

        User user1 = new User();
        user1.setUserName("id@test.com");
        user1.setPassword(passwordEncoder.encode("password"));
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setEmail("john.d@test.com");
        user1.setPhoneNumber("010-1234-5678");
        user1.setEnabled(true);
        user1.setAuthorities(authorityList1);
        userDetailsRepository.save(user1);
    }

    private Authority createAuthority(String roleCode, String roleDescription) {
        Authority authority = new Authority();
        authority.setRoleCode(roleCode);
        authority.setRoleDescription(roleDescription);
        return authority;
    }
}

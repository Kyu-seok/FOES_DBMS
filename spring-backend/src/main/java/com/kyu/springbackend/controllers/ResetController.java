package com.kyu.springbackend.controllers;

import com.kyu.springbackend.model.*;
import com.kyu.springbackend.model.user.Authority;
import com.kyu.springbackend.model.user.User;
import com.kyu.springbackend.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/reset")
@AllArgsConstructor
public class ResetController {

    private final AssetRepository assetRepository;
    private final KtpUsrRepository ktpUsrRepository;
    private final MobilityRepository mobilityRepository;
    private final MouMoaRepository mouMoaRepository;
    private final ResearchAwardRepository researchAwardRepository;
    private final StaffRepository staffRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public void resetDatabase() {
        deleteAllCollection();
        loadUserDetails();
        loadStaffs();
        loadAssets();
        loadKtpUsrs();
        loadMobility();
        loadMouMoas();
        loadResearchAwards();
    }

    private void deleteAllCollection() {
        userDetailsRepository.deleteAll();
        staffRepository.deleteAll();
        assetRepository.deleteAll();
        ktpUsrRepository.deleteAll();
        mobilityRepository.deleteAll();
        mouMoaRepository.deleteAll();
        researchAwardRepository.deleteAll();
    }

    private void loadUserDetails() {
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

        User user2 = new User();
        user2.setUserName("terence@test.com");
        user2.setPassword(passwordEncoder.encode("password"));
        user2.setFirstName("Terence");
        user2.setLastName("Park");
        user2.setEmail("terence.p@test.com");
        user2.setPhoneNumber("010-1234-1234");
        user2.setEnabled(true);
        user2.setAuthorities(authorityList1);
        userDetailsRepository.save(user2);

        User user3 = new User();
        user3.setUserName("saveethya@test.com");
        user3.setPassword(passwordEncoder.encode("password"));
        user3.setFirstName("Saveethya");
        user3.setLastName("Kumar");
        user3.setEmail("savvethya.k@test.com");
        user3.setPhoneNumber("010-1234-1334");
        user3.setEnabled(true);
        user3.setAuthorities(authorityList1);
        userDetailsRepository.save(user3);

        User user4 = new User();
        user4.setUserName("foad@test.com");
        user4.setPassword(passwordEncoder.encode("password"));
        user4.setFirstName("Foad");
        user4.setLastName("Motellabi");
        user4.setEmail("foad.m@test.com");
        user4.setPhoneNumber("010-1234-1004");
        user4.setEnabled(true);
        user4.setAuthorities(authorityList1);
        userDetailsRepository.save(user4);

        User user5 = new User();
        user5.setUserName("thomas@test.com");
        user5.setPassword(passwordEncoder.encode("password"));
        user5.setFirstName("Thomas");
        user5.setLastName("Basuki");
        user5.setEmail("thomas.b@test.com");
        user5.setPhoneNumber("010-1234-1005");
        user5.setEnabled(true);
        user5.setAuthorities(authorityList1);
        userDetailsRepository.save(user5);

        User user6 = new User();
        user6.setUserName("adam@test.com");
        user6.setPassword(passwordEncoder.encode("password"));
        user6.setFirstName("Adam");
        user6.setLastName("Smith");
        user6.setEmail("adam.s@test.com");
        user6.setPhoneNumber("010-1234-1006");
        user6.setEnabled(true);
        user6.setAuthorities(authorityList1);
        userDetailsRepository.save(user6);

        User user7 = new User();
        user7.setUserName("denise@test.com");
        user7.setPassword(passwordEncoder.encode("password"));
        user7.setFirstName("Denise");
        user7.setLastName("Lee");
        user7.setEmail("denise.l@test.com");
        user7.setPhoneNumber("010-1234-1007");
        user7.setEnabled(true);
        user7.setAuthorities(authorityList1);
        userDetailsRepository.save(user7);

        User user8 = new User();
        user8.setUserName("john@test.com");
        user8.setPassword(passwordEncoder.encode("password"));
        user8.setFirstName("John");
        user8.setLastName("Carmack");
        user8.setEmail("john.k@test.com");
        user8.setPhoneNumber("010-1234-1008");
        user8.setEnabled(true);
        user8.setAuthorities(authorityList1);
        userDetailsRepository.save(user8);

        User user9 = new User();
        user9.setUserName("james@test.com");
        user9.setPassword(passwordEncoder.encode("password"));
        user9.setFirstName("James");
        user9.setLastName("Gosling");
        user9.setEmail("james.g@test.com");
        user9.setPhoneNumber("010-1234-1009");
        user9.setEnabled(true);
        user9.setAuthorities(authorityList1);
        userDetailsRepository.save(user9);

    }

    private void loadStaffs() {

        Staff staff0 = new Staff();
        staff0.setFirstName("Adam");
        staff0.setLastName("Smith");
        staff0.setTitle("Dr.");
        staff0.setStaffId("20000001");
        staff0.setEmail("adam.smith@email.com");
        staff0.setDepartment("Faculty Office");
        staff0.setPosition("Dean");
        staff0.setRoomNo("SK3 301");
        staff0.setExtNo("100");
        staff0.setStatus("Permanent");
        staff0.setAppointmentLevel("Dean");
        staff0.setReportDutyDate("2023-01-30");
        staff0.setPhotocopyId("1024");
        staff0.setPigeonboxNo("box 1");
        staffRepository.save(staff0);

        Staff staff1 = new Staff();
        staff1.setFirstName("Bob");
        staff1.setLastName("Marley");
        staff1.setTitle("Ir.");
        staff1.setStaffId("20000002");
        staff1.setEmail("bob.marley@email.com");
        staff1.setDepartment("Lab");
        staff1.setPosition("Associate Dean R&D");
        staff1.setRoomNo("SK3 302");
        staff1.setExtNo("102");
        staff1.setStatus("Permanent");
        staff1.setAppointmentLevel("Professor");
        staff1.setReportDutyDate("2023-01-30");
        staff1.setPhotocopyId("1002");
        staff1.setPigeonboxNo("box 2");
        staffRepository.save(staff1);


        Staff staff2 = new Staff();
        staff2.setFirstName("Chloe");
        staff2.setLastName("Kim");
        staff2.setTitle("Mrs.");
        staff2.setStaffId("20000003");
        staff2.setEmail("chloe.kim@email.com");
        staff2.setDepartment("Electrical & Computer");
        staff2.setPosition("Associate Dean T&L");
        staff2.setRoomNo("SK3 303");
        staff2.setExtNo("103");
        staff2.setStatus("Contract");
        staff2.setAppointmentLevel("Lecturer");
        staff2.setReportDutyDate("2023-01-30");
        staff2.setPhotocopyId("1003");
        staff2.setPigeonboxNo("box 3");
        staffRepository.save(staff2);

        Staff staff3 = new Staff();
        staff3.setFirstName("Daniel");
        staff3.setLastName("Kang");
        staff3.setTitle("Mr.");
        staff3.setStaffId("20000004");
        staff3.setEmail("daniel.kang@email.com");
        staff3.setDepartment("Applied Sciences");
        staff3.setPosition("HOD");
        staff3.setRoomNo("SK3 304");
        staff3.setExtNo("104");
        staff3.setStatus("Contract");
        staff3.setAppointmentLevel("Lecturer");
        staff3.setReportDutyDate("2023-01-30");
        staff3.setPhotocopyId("1004");
        staff3.setPigeonboxNo("box 4");
        staffRepository.save(staff3);

        Staff staff4 = new Staff();
        staff4.setFirstName("Elton");
        staff4.setLastName("John");
        staff4.setTitle("Mr.");
        staff4.setStaffId("20000005");
        staff4.setEmail("Elton.John@email.com");
        staff4.setDepartment("Chemical & Energy");
        staff4.setPosition("Programme Coordinator");
        staff4.setRoomNo("SK3 305");
        staff4.setExtNo("105");
        staff4.setStatus("Contract");
        staff4.setAppointmentLevel("Lecturer");
        staff4.setReportDutyDate("2023-01-30");
        staff4.setPhotocopyId("1005");
        staff4.setPigeonboxNo("box 5");
        staffRepository.save(staff4);

        Staff staff5 = new Staff();
        staff5.setFirstName("Emilio");
        staff5.setLastName("Estevez");
        staff5.setTitle("Mr.");
        staff5.setStaffId("20000006");
        staff5.setEmail("emilio.estevez@email.com");
        staff5.setDepartment("Applied Sciences");
        staff5.setPosition("Committee Chari");
        staff5.setRoomNo("SK3 306");
        staff5.setExtNo("106");
        staff5.setStatus("Contract");
        staff5.setAppointmentLevel("Lecturer");
        staff5.setReportDutyDate("2023-01-30");
        staff5.setPhotocopyId("1006");
        staff5.setPigeonboxNo("box 6");
        staffRepository.save(staff5);

        Staff staff6 = new Staff();
        staff6.setFirstName("Jim");
        staff6.setLastName("Edmonds");
        staff6.setTitle("Mr.");
        staff6.setStaffId("20000007");
        staff6.setEmail("jim.edmonds@email.com");
        staff6.setDepartment("Mechanical");
        staff6.setPosition("Research Cluster Head");
        staff6.setRoomNo("SK3 307");
        staff6.setExtNo("107");
        staff6.setStatus("Contract");
        staff6.setAppointmentLevel("Lecturer");
        staff6.setReportDutyDate("2023-01-30");
        staff6.setPhotocopyId("1007");
        staff6.setPigeonboxNo("box 7");
        staffRepository.save(staff6);

        Staff staff7 = new Staff();
        staff7.setFirstName("Lisa");
        staff7.setLastName("Edelstein");
        staff7.setTitle("Mrs.");
        staff7.setStaffId("20000008");
        staff7.setEmail("lisa.edelstein@email.com");
        staff7.setDepartment("Mechanical");
        staff7.setPosition("Group Head");
        staff7.setRoomNo("SK3 308");
        staff7.setExtNo("108");
        staff7.setStatus("Contract");
        staff7.setAppointmentLevel("Lecturer");
        staff7.setReportDutyDate("2023-01-30");
        staff7.setPhotocopyId("1008");
        staff7.setPigeonboxNo("box 8");
        staffRepository.save(staff7);

        Staff staff8 = new Staff();
        staff8.setFirstName("Max");
        staff8.setLastName("Ehrich");
        staff8.setTitle("Mr.");
        staff8.setStaffId("20000009");
        staff8.setEmail("max.ehrich@email.com");
        staff8.setDepartment("Civil & Construction");
        staff8.setPosition("Faculty manager");
        staff8.setRoomNo("SK3 309");
        staff8.setExtNo("109");
        staff8.setStatus("Contract");
        staff8.setAppointmentLevel("Lecturer");
        staff8.setReportDutyDate("2023-01-30");
        staff8.setPhotocopyId("1009");
        staff8.setPigeonboxNo("box 9");
        staffRepository.save(staff8);

        Staff staff9 = new Staff();
        staff9.setFirstName("Randall");
        staff9.setLastName("Emmett");
        staff9.setTitle("Mr.");
        staff9.setStaffId("20000010");
        staff9.setEmail("randall.emmett@email.com");
        staff9.setDepartment("Chemical & Energy");
        staff9.setPosition("Senior Admin Assistant");
        staff9.setRoomNo("SK3 310");
        staff9.setExtNo("110");
        staff9.setStatus("Contract");
        staff9.setAppointmentLevel("Lecturer");
        staff9.setReportDutyDate("2023-01-30");
        staff9.setPhotocopyId("1010");
        staff9.setPigeonboxNo("box 1");
        staffRepository.save(staff9);
    }

    private void loadAssets() {

        Asset asset0 = new Asset();
        asset0.setItem("Desk");
        asset0.setDescription("Standing desk");
        asset0.setSerialNo("JBD001");
        asset0.setBrand("Jarvis");
        asset0.setQuantity(10);
        asset0.setYearPurchased("2023");
        asset0.setOriginalCost("599");
        asset0.setGrant("0");
        asset0.setWarranty("2 years");
        asset0.setModelNo("JBD001");
        asset0.setPhysicalCheck("done");
        asset0.setConditionOfAsset("default");
        asset0.setAssetTagNumber("01-10234-1234");
        asset0.setLocation("SK3 301");
        asset0.setEndUser("Lecturer");
        assetRepository.save(asset0);

        Asset asset1 = new Asset();
        asset1.setItem("Chair");
        asset1.setDescription("Carbon leather chair");
        asset1.setSerialNo("HMA001");
        asset1.setBrand("Herman Miller");
        asset1.setQuantity(11);
        asset1.setYearPurchased("2023");
        asset1.setOriginalCost("1195");
        asset1.setGrant("0");
        asset1.setWarranty("3 years");
        asset1.setModelNo("HMA001");
        asset1.setPhysicalCheck("done");
        asset1.setConditionOfAsset("default");
        asset1.setAssetTagNumber("01-2134-12241");
        asset1.setLocation("SK3 301");
        asset1.setEndUser("Lecturer");
        assetRepository.save(asset1);

        Asset asset2 = new Asset();
        asset2.setItem("Cushion");
        asset2.setDescription("Seat Cushion");
        asset2.setSerialNo("PRSC001");
        asset2.setBrand("Jarvis");
        asset2.setQuantity(11);
        asset2.setYearPurchased("2023");
        asset2.setOriginalCost("99");
        asset2.setGrant("12");
        asset2.setWarranty("1 years");
        asset2.setModelNo("PRSC001");
        asset2.setPhysicalCheck("done");
        asset2.setConditionOfAsset("default");
        asset2.setAssetTagNumber("01-1523-1234");
        asset2.setLocation("SK3 301");
        asset2.setEndUser("Lecturer");
        assetRepository.save(asset2);

        Asset asset3 = new Asset();
        asset3.setItem("Lamp");
        asset3.setDescription("Work Lamp");
        asset3.setSerialNo("IFC001");
        asset3.setBrand("Ikea");
        asset3.setQuantity(12);
        asset3.setYearPurchased("2023");
        asset3.setOriginalCost("27");
        asset3.setGrant("0");
        asset3.setWarranty("1 years");
        asset3.setModelNo("IFC001");
        asset3.setPhysicalCheck("done");
        asset3.setConditionOfAsset("default");
        asset3.setAssetTagNumber("01-5122-1234");
        asset3.setLocation("SK3 301");
        asset3.setEndUser("Lecturer");
        assetRepository.save(asset3);

        Asset asset4 = new Asset();
        asset4.setItem("iMac");
        asset4.setDescription("Apple iMac");
        asset4.setSerialNo("AI001");
        asset4.setBrand("Apple");
        asset4.setQuantity(15);
        asset4.setYearPurchased("2023");
        asset4.setOriginalCost("1699");
        asset4.setGrant("0");
        asset4.setWarranty("2 years");
        asset4.setModelNo("AI001");
        asset4.setPhysicalCheck("done");
        asset4.setConditionOfAsset("default");
        asset4.setAssetTagNumber("01-10234-1234");
        asset4.setLocation("SK3 303");
        asset4.setEndUser("Lecturer");
        assetRepository.save(asset4);

        Asset asset5 = new Asset();
        asset5.setItem("Magic Keyboard");
        asset5.setDescription("Apple Magic Keyboard");
        asset5.setSerialNo("AI002");
        asset5.setBrand("Apple");
        asset5.setQuantity(15);
        asset5.setYearPurchased("2023");
        asset5.setOriginalCost("100");
        asset5.setGrant("0");
        asset5.setWarranty("3 years");
        asset5.setModelNo("AI002");
        asset5.setPhysicalCheck("done");
        asset5.setConditionOfAsset("default");
        asset5.setAssetTagNumber("01-10234-1234");
        asset5.setLocation("SK3 303");
        asset5.setEndUser("Lecturer");
        assetRepository.save(asset5);

        Asset asset6 = new Asset();
        asset6.setItem("MX Master 3");
        asset6.setDescription("Logitech MX Master 3");
        asset6.setSerialNo("LMXM001");
        asset6.setBrand("Logitech");
        asset6.setQuantity(15);
        asset6.setYearPurchased("2022");
        asset6.setOriginalCost("95");
        asset6.setGrant("0");
        asset6.setWarranty("3 years");
        asset6.setModelNo("LMXM001");
        asset6.setPhysicalCheck("done");
        asset6.setConditionOfAsset("default");
        asset6.setAssetTagNumber("01-10234-1234");
        asset6.setLocation("SK3 303");
        asset6.setEndUser("Lecturer");
        assetRepository.save(asset6);

        Asset asset7 = new Asset();
        asset7.setItem("Beam Projector");
        asset7.setDescription("HD Epson Beam Projector");
        asset7.setSerialNo("EBP001");
        asset7.setBrand("Epson");
        asset7.setQuantity(15);
        asset7.setYearPurchased("2022");
        asset7.setOriginalCost("999");
        asset7.setGrant("0");
        asset7.setWarranty("1 years");
        asset7.setModelNo("EBP001");
        asset7.setPhysicalCheck("done");
        asset7.setConditionOfAsset("default");
        asset7.setAssetTagNumber("01-10234-1234");
        asset7.setLocation("SK3 303");
        asset7.setEndUser("Student");
        assetRepository.save(asset7);

    }

    private void loadKtpUsrs() {

        KtpUsr ktpUsr1 = new KtpUsr();
        ktpUsr1.setProgramName("Applied Science Program");
        ktpUsr1.setCommunityIndustryName("Applied Sciences");
        ktpUsr1.setDate("2023-01-01");
        ktpUsr1.setLocation("In Campus");
        ktpUsr1.setLeadBy("Dr. Fridman");
        ktpUsr1.setFaculty("Applied Sciences");
        ktpUsr1.setPartnerName("NASA");
        ktpUsr1.setNoOfStaff("12");
        ktpUsr1.setNoOfStudent("20");
        ktpUsr1.setInternalFunding("10000");
        ktpUsr1.setExternalFunding("14000");
        ktpUsrRepository.save(ktpUsr1);

        KtpUsr ktpUsr2 = new KtpUsr();
        ktpUsr2.setProgramName("Chemical engineering program");
        ktpUsr2.setCommunityIndustryName("Chemical engineering");
        ktpUsr2.setDate("2023-02-02");
        ktpUsr2.setLocation("In Campus");
        ktpUsr2.setLeadBy("Dr. James Gosling");
        ktpUsr2.setFaculty("Chemical Engineering");
        ktpUsr2.setPartnerName("Petronas");
        ktpUsr2.setNoOfStaff("82");
        ktpUsr2.setNoOfStudent("92");
        ktpUsr2.setInternalFunding("15000");
        ktpUsr2.setExternalFunding("9000");
        ktpUsrRepository.save(ktpUsr2);

        KtpUsr ktpUsr3 = new KtpUsr();
        ktpUsr3.setProgramName("Energy engineering program");
        ktpUsr3.setCommunityIndustryName("Energy engineering");
        ktpUsr3.setDate("2023-02-18");
        ktpUsr3.setLocation("In Campus");
        ktpUsr3.setLeadBy("Dr. Guido van Rossum");
        ktpUsr3.setFaculty("Energy Engineering");
        ktpUsr3.setPartnerName("Green energy .Co");
        ktpUsr3.setNoOfStaff("57");
        ktpUsr3.setNoOfStudent("92");
        ktpUsr3.setInternalFunding("16000");
        ktpUsr3.setExternalFunding("9200");
        ktpUsrRepository.save(ktpUsr3);

        KtpUsr ktpUsr4 = new KtpUsr();
        ktpUsr4.setProgramName("Civil engineering program");
        ktpUsr4.setCommunityIndustryName("Civil engineering");
        ktpUsr4.setDate("2023-12-22");
        ktpUsr4.setLocation("In Campus");
        ktpUsr4.setLeadBy("Dr. John Carmack");
        ktpUsr4.setFaculty("Civil Engineering");
        ktpUsr4.setPartnerName("Samsung C&T");
        ktpUsr4.setNoOfStaff("17");
        ktpUsr4.setNoOfStudent("95");
        ktpUsr4.setInternalFunding("56000");
        ktpUsr4.setExternalFunding("58000");
        ktpUsrRepository.save(ktpUsr4);

        KtpUsr ktpUsr5 = new KtpUsr();
        ktpUsr5.setProgramName("Electrical engineering program");
        ktpUsr5.setCommunityIndustryName("Electrical engineering");
        ktpUsr5.setDate("2023-07-02");
        ktpUsr5.setLocation("In Campus");
        ktpUsr5.setLeadBy("Dr. Thomas Lay");
        ktpUsr5.setFaculty("Electrical Engineering");
        ktpUsr5.setPartnerName("Bell lab");
        ktpUsr5.setNoOfStaff("18");
        ktpUsr5.setNoOfStudent("19");
        ktpUsr5.setInternalFunding("50000");
        ktpUsr5.setExternalFunding("9000");
        ktpUsrRepository.save(ktpUsr5);

        KtpUsr ktpUsr6 = new KtpUsr();
        ktpUsr6.setProgramName("Computer engineering program");
        ktpUsr6.setCommunityIndustryName("Computer engineering");
        ktpUsr6.setDate("2023-12-02");
        ktpUsr6.setLocation("In Campus");
        ktpUsr6.setLeadBy("Dr. Brian Kernighan");
        ktpUsr6.setFaculty("Computer Engineering");
        ktpUsr6.setPartnerName("Nottingham University");
        ktpUsr6.setNoOfStaff("28");
        ktpUsr6.setNoOfStudent("29");
        ktpUsr6.setInternalFunding("51000");
        ktpUsr6.setExternalFunding("2000");
        ktpUsrRepository.save(ktpUsr6);

        KtpUsr ktpUsr7 = new KtpUsr();
        ktpUsr7.setProgramName("Mechanical engineering program");
        ktpUsr7.setCommunityIndustryName("Mechanical engineering");
        ktpUsr7.setDate("2023-03-18");
        ktpUsr7.setLocation("In Campus");
        ktpUsr7.setLeadBy("Dr. Liv Boeree");
        ktpUsr7.setFaculty("Mechanical Engineering");
        ktpUsr7.setPartnerName("General Motors");
        ktpUsr7.setNoOfStaff("73");
        ktpUsr7.setNoOfStudent("83");
        ktpUsr7.setInternalFunding("26000");
        ktpUsr7.setExternalFunding("1800");
        ktpUsrRepository.save(ktpUsr7);

    }

    private void loadMouMoas() {

        MouMoa mouMoa1 = new MouMoa();
        mouMoa1.setCountry("America");
        mouMoa1.setInstitution("University of America");
        mouMoa1.setSignedDate("2023-01-01");
        mouMoa1.setDueDate("2028-01-01");
        mouMoa1.setAreaOfCollaboration("Applied Science");
        mouMoa1.setProgress("10%");
        mouMoa1.setTypeOfAgreement("MOU");
        mouMoa1.setResearch("Applied Science");
        mouMoa1.setTeaching("2");
        mouMoa1.setExchange("20");
        mouMoa1.setCollaborationAndPartnerships("American Partnership");
        mouMoa1.setMutualExtension("None");
        mouMoaRepository.save(mouMoa1);

        MouMoa mouMoa2 = new MouMoa();
        mouMoa2.setCountry("Brazil");
        mouMoa2.setInstitution("University of Brazil");
        mouMoa2.setSignedDate("2023-11-21");
        mouMoa2.setDueDate("2028-11-21");
        mouMoa2.setAreaOfCollaboration("Chemical Engineering");
        mouMoa2.setProgress("21%");
        mouMoa2.setTypeOfAgreement("MOU");
        mouMoa2.setResearch("Chemical Engineering");
        mouMoa2.setTeaching("10");
        mouMoa2.setExchange("21");
        mouMoa2.setCollaborationAndPartnerships("Brazil Partnership");
        mouMoa2.setMutualExtension("None");
        mouMoaRepository.save(mouMoa2);

        MouMoa mouMoa3 = new MouMoa();
        mouMoa3.setCountry("Canada");
        mouMoa3.setInstitution("University of Canada");
        mouMoa3.setSignedDate("2023-12-11");
        mouMoa3.setDueDate("2028-12-11");
        mouMoa3.setAreaOfCollaboration("Energy Engineering");
        mouMoa3.setProgress("28%");
        mouMoa3.setTypeOfAgreement("MOU");
        mouMoa3.setResearch("Energy Engineering");
        mouMoa3.setTeaching("19");
        mouMoa3.setExchange("11");
        mouMoa3.setCollaborationAndPartnerships("Canada Partnership");
        mouMoa3.setMutualExtension("None");
        mouMoaRepository.save(mouMoa3);

        MouMoa mouMoa4 = new MouMoa();
        mouMoa4.setCountry("Denmark");
        mouMoa4.setInstitution("University of Denmark");
        mouMoa4.setSignedDate("2023-07-13");
        mouMoa4.setDueDate("2028-07-13");
        mouMoa4.setAreaOfCollaboration("Civil Engineering");
        mouMoa4.setProgress("27%");
        mouMoa4.setTypeOfAgreement("MOU");
        mouMoa4.setResearch("Civil Engineering");
        mouMoa4.setTeaching("10");
        mouMoa4.setExchange("21");
        mouMoa4.setCollaborationAndPartnerships("Denmark Partnership");
        mouMoa4.setMutualExtension("None");
        mouMoaRepository.save(mouMoa4);

        MouMoa mouMoa5 = new MouMoa();
        mouMoa5.setCountry("England");
        mouMoa5.setInstitution("University of England");
        mouMoa5.setSignedDate("2023-06-22");
        mouMoa5.setDueDate("2028-06-22");
        mouMoa5.setAreaOfCollaboration("Construction Engineering");
        mouMoa5.setProgress("32%");
        mouMoa5.setTypeOfAgreement("MOA");
        mouMoa5.setResearch("Construction Engineering");
        mouMoa5.setTeaching("17");
        mouMoa5.setExchange("31");
        mouMoa5.setCollaborationAndPartnerships("England Partnership");
        mouMoa5.setMutualExtension("Yes");
        mouMoaRepository.save(mouMoa5);

        MouMoa mouMoa6 = new MouMoa();
        mouMoa6.setCountry("Germany");
        mouMoa6.setInstitution("University of Germany");
        mouMoa6.setSignedDate("2023-06-21");
        mouMoa6.setDueDate("2028-06-21");
        mouMoa6.setAreaOfCollaboration("Electrical Engineering");
        mouMoa6.setProgress("37%");
        mouMoa6.setTypeOfAgreement("MOA");
        mouMoa6.setResearch("Electrical Engineering");
        mouMoa6.setTeaching("35");
        mouMoa6.setExchange("58");
        mouMoa6.setCollaborationAndPartnerships("Germany Partnership");
        mouMoa6.setMutualExtension("Yes");
        mouMoaRepository.save(mouMoa6);

        MouMoa mouMoa7 = new MouMoa();
        mouMoa7.setCountry("Japan");
        mouMoa7.setInstitution("University of Japan");
        mouMoa7.setSignedDate("2023-03-24");
        mouMoa7.setDueDate("2028-03-24");
        mouMoa7.setAreaOfCollaboration("Computer Engineering");
        mouMoa7.setProgress("42%");
        mouMoa7.setTypeOfAgreement("MOA");
        mouMoa7.setResearch("Computer Engineering");
        mouMoa7.setTeaching("58");
        mouMoa7.setExchange("12");
        mouMoa7.setCollaborationAndPartnerships("Japan Partnership");
        mouMoa7.setMutualExtension("Yes");
        mouMoaRepository.save(mouMoa7);

        MouMoa mouMoa8 = new MouMoa();
        mouMoa8.setCountry("Korea");
        mouMoa8.setInstitution("University of Korea");
        mouMoa8.setSignedDate("2023-10-08");
        mouMoa8.setDueDate("2028-10-08");
        mouMoa8.setAreaOfCollaboration("Mechanical Engineering");
        mouMoa8.setProgress("62%");
        mouMoa8.setTypeOfAgreement("MOA");
        mouMoa8.setResearch("Mechanical Engineering");
        mouMoa8.setTeaching("58");
        mouMoa8.setExchange("12");
        mouMoa8.setCollaborationAndPartnerships("Korea Partnership");
        mouMoa8.setMutualExtension("Yes");
        mouMoaRepository.save(mouMoa8);

    }

    private void loadMobility() {
        Mobility mobility1 = new Mobility();
        mobility1.setStaffOrStudent("Student");
        mobility1.setInOrOutBound("Out Bound");
        mobility1.setName("Sumati Alfred");
        mobility1.setAttendeeId("10000001");
        mobility1.setProgram("Student Exchange");
        mobility1.setNameOfUniversity("Monash University");
        mobility1.setCountry("Australia");
        mobility1.setDuration("2 Semester");
        mobility1.setFromDate("2021-01-01");
        mobility1.setToDate("2022-01-01");
        mobilityRepository.save(mobility1);

        Mobility mobility2 = new Mobility();
        mobility2.setStaffOrStudent("Staff");
        mobility2.setInOrOutBound("Out Bound");
        mobility2.setName("Clidna Maxentius");
        mobility2.setAttendeeId("10000002");
        mobility2.setProgram("Staff Exchange");
        mobility2.setNameOfUniversity("Nottingham University");
        mobility2.setCountry("Australia");
        mobility2.setDuration("2 Semester");
        mobility2.setFromDate("2021-01-01");
        mobility2.setToDate("2022-01-01");
        mobilityRepository.save(mobility2);


        Mobility mobility3 = new Mobility();
        mobility3.setStaffOrStudent("Staff");
        mobility3.setInOrOutBound("Out Bound");
        mobility3.setName("Mikulas Dorrit");
        mobility3.setAttendeeId("10000003");
        mobility3.setProgram("Staff Exchange");
        mobility3.setNameOfUniversity("Monash University");
        mobility3.setCountry("Australia");
        mobility3.setDuration("3 Semester");
        mobility3.setFromDate("2021-01-01");
        mobility3.setToDate("2022-07-01");
        mobilityRepository.save(mobility3);

        Mobility mobility4 = new Mobility();
        mobility4.setStaffOrStudent("Student");
        mobility4.setInOrOutBound("Out Bound");
        mobility4.setName("Francisco Amanda");
        mobility4.setAttendeeId("10000004");
        mobility4.setProgram("Student Exchange");
        mobility4.setNameOfUniversity("Monash University");
        mobility4.setCountry("Australia");
        mobility4.setDuration("1 Semester");
        mobility4.setFromDate("2021-01-01");
        mobility4.setToDate("2021-07-01");
        mobilityRepository.save(mobility4);

        Mobility mobility5 = new Mobility();
        mobility5.setStaffOrStudent("Student");
        mobility5.setInOrOutBound("Out Bound");
        mobility5.setName("Eva Kiersten");
        mobility5.setAttendeeId("10000005");
        mobility5.setProgram("Student Exchange");
        mobility5.setNameOfUniversity("Monash University");
        mobility5.setCountry("Australia");
        mobility5.setDuration("1 Semester");
        mobility5.setFromDate("2021-01-01");
        mobility5.setToDate("2021-07-01");
        mobilityRepository.save(mobility5);

        Mobility mobility6 = new Mobility();
        mobility6.setStaffOrStudent("Student");
        mobility6.setInOrOutBound("Out Bound");
        mobility6.setName("Saveriu Stefanija");
        mobility6.setAttendeeId("10000006");
        mobility6.setProgram("Student Exchange");
        mobility6.setNameOfUniversity("Nottingham University");
        mobility6.setCountry("Australia");
        mobility6.setDuration("3 Semester");
        mobility6.setFromDate("2021-01-01");
        mobility6.setToDate("2022-07-01");
        mobilityRepository.save(mobility6);

        Mobility mobility7 = new Mobility();
        mobility7.setStaffOrStudent("Student");
        mobility7.setInOrOutBound("Out Bound");
        mobility7.setName("Dusanka Manfred");
        mobility7.setAttendeeId("10000007");
        mobility7.setProgram("Student Exchange");
        mobility7.setNameOfUniversity("Nottingham University");
        mobility7.setCountry("Australia");
        mobility7.setDuration("2 Semester");
        mobility7.setFromDate("2021-01-01");
        mobility7.setToDate("2022-01-01");
        mobilityRepository.save(mobility7);

        Mobility mobility8 = new Mobility();
        mobility8.setStaffOrStudent("Student");
        mobility8.setInOrOutBound("Out Bound");
        mobility8.setName("Evgeniy Foma");
        mobility8.setAttendeeId("10000008");
        mobility8.setProgram("Student Exchange");
        mobility8.setNameOfUniversity("Nottingham University");
        mobility8.setCountry("Australia");
        mobility8.setDuration("1 Semester");
        mobility8.setFromDate("2021-01-01");
        mobility8.setToDate("2021-07-01");
        mobilityRepository.save(mobility8);

        Mobility mobility9 = new Mobility();
        mobility9.setStaffOrStudent("Student");
        mobility9.setInOrOutBound("Out Bound");
        mobility9.setName("Arethusa Iqaluk");
        mobility9.setAttendeeId("10000009");
        mobility9.setProgram("Student Exchange");
        mobility9.setNameOfUniversity("Monash University");
        mobility9.setCountry("Australia");
        mobility9.setDuration("3 Semester");
        mobility9.setFromDate("2021-01-01");
        mobility9.setToDate("2022-07-01");
        mobilityRepository.save(mobility9);

        Mobility mobility10 = new Mobility();
        mobility10.setStaffOrStudent("Student");
        mobility10.setInOrOutBound("Out Bound");
        mobility10.setName("Milka Kerberos");
        mobility10.setAttendeeId("10000010");
        mobility10.setProgram("Student Exchange");
        mobility10.setNameOfUniversity("Nottingham University");
        mobility10.setCountry("Australia");
        mobility10.setDuration("2 Semester");
        mobility10.setFromDate("2021-01-01");
        mobility10.setToDate("2022-01-01");
        mobilityRepository.save(mobility10);

        Mobility mobility11 = new Mobility();
        mobility11.setStaffOrStudent("Student");
        mobility11.setInOrOutBound("In Bound");
        mobility11.setName("Vasundhara Sitti");
        mobility11.setAttendeeId("10000011");
        mobility11.setProgram("Student Exchange");
        mobility11.setNameOfUniversity("Monash University");
        mobility11.setCountry("Australia");
        mobility11.setDuration("2 Semester");
        mobility11.setFromDate("2021-01-01");
        mobility11.setToDate("2022-01-01");
        mobilityRepository.save(mobility11);

        Mobility mobility12 = new Mobility();
        mobility12.setStaffOrStudent("Student");
        mobility12.setInOrOutBound("In Bound");
        mobility12.setName("Rosa Salvador");
        mobility12.setAttendeeId("10000012");
        mobility12.setProgram("Student Exchange");
        mobility12.setNameOfUniversity("Monash University");
        mobility12.setCountry("Australia");
        mobility12.setDuration("2 Semester");
        mobility12.setFromDate("2021-01-01");
        mobility12.setToDate("2022-01-01");
        mobilityRepository.save(mobility12);

        Mobility mobility13 = new Mobility();
        mobility13.setStaffOrStudent("Student");
        mobility13.setInOrOutBound("In Bound");
        mobility13.setName("Rohesia Artair");
        mobility13.setAttendeeId("10000013");
        mobility13.setProgram("Student Exchange");
        mobility13.setNameOfUniversity("Monash University");
        mobility13.setCountry("Australia");
        mobility13.setDuration("2 Semester");
        mobility13.setFromDate("2021-01-01");
        mobility13.setToDate("2022-01-01");
        mobilityRepository.save(mobility13);

        Mobility mobility14 = new Mobility();
        mobility14.setStaffOrStudent("Student");
        mobility14.setInOrOutBound("In Bound");
        mobility14.setName("Petronila Gayathri");
        mobility14.setAttendeeId("10000014");
        mobility14.setProgram("Student Exchange");
        mobility14.setNameOfUniversity("Nottingham University");
        mobility14.setCountry("Australia");
        mobility14.setDuration("2 Semester");
        mobility14.setFromDate("2021-01-01");
        mobility14.setToDate("2022-01-01");
        mobilityRepository.save(mobility14);

        Mobility mobility15 = new Mobility();
        mobility15.setStaffOrStudent("Student");
        mobility15.setInOrOutBound("In Bound");
        mobility15.setName("Noemi Sennacherib");
        mobility15.setAttendeeId("10000015");
        mobility15.setProgram("Student Exchange");
        mobility15.setNameOfUniversity("Monash University");
        mobility15.setCountry("Australia");
        mobility15.setDuration("2 Semester");
        mobility15.setFromDate("2021-01-01");
        mobility15.setToDate("2022-01-01");
        mobilityRepository.save(mobility15);

        Mobility mobility16 = new Mobility();
        mobility16.setStaffOrStudent("Student");
        mobility16.setInOrOutBound("In Bound");
        mobility16.setName("Neha Mitsuko");
        mobility16.setAttendeeId("10000016");
        mobility16.setProgram("Student Exchange");
        mobility16.setNameOfUniversity("Monash University");
        mobility16.setCountry("Australia");
        mobility16.setDuration("2 Semester");
        mobility16.setFromDate("2021-01-01");
        mobility16.setToDate("2022-01-01");
        mobilityRepository.save(mobility16);

        Mobility mobility17 = new Mobility();
        mobility17.setStaffOrStudent("Student");
        mobility17.setInOrOutBound("In Bound");
        mobility17.setName("Steen Kyrillos");
        mobility17.setAttendeeId("10000017");
        mobility17.setProgram("Student Exchange");
        mobility17.setNameOfUniversity("Monash University");
        mobility17.setCountry("Australia");
        mobility17.setDuration("2 Semester");
        mobility17.setFromDate("2021-01-01");
        mobility17.setToDate("2022-01-01");
        mobilityRepository.save(mobility17);

        Mobility mobility18 = new Mobility();
        mobility18.setStaffOrStudent("Student");
        mobility18.setInOrOutBound("In Bound");
        mobility18.setName("Efe Stefanija");
        mobility18.setAttendeeId("10000018");
        mobility18.setProgram("Student Exchange");
        mobility18.setNameOfUniversity("Monash University");
        mobility18.setCountry("Australia");
        mobility18.setDuration("2 Semester");
        mobility18.setFromDate("2021-01-01");
        mobility18.setToDate("2022-01-01");
        mobilityRepository.save(mobility18);

        Mobility mobility19 = new Mobility();
        mobility19.setStaffOrStudent("Student");
        mobility19.setInOrOutBound("In Bound");
        mobility19.setName("Samuel Milla");
        mobility19.setAttendeeId("10000019");
        mobility19.setProgram("Student Exchange");
        mobility19.setNameOfUniversity("Nottingham University");
        mobility19.setCountry("Australia");
        mobility19.setDuration("2 Semester");
        mobility19.setFromDate("2021-01-01");
        mobility19.setToDate("2022-01-01");
        mobilityRepository.save(mobility19);

        Mobility mobility20 = new Mobility();
        mobility20.setStaffOrStudent("Student");
        mobility20.setInOrOutBound("In Bound");
        mobility20.setName("Amalija Wattana");
        mobility20.setAttendeeId("10000020");
        mobility20.setProgram("Student Exchange");
        mobility20.setNameOfUniversity("Monash University");
        mobility20.setCountry("Australia");
        mobility20.setDuration("2 Semester");
        mobility20.setFromDate("2021-01-01");
        mobility20.setToDate("2022-01-01");
        mobilityRepository.save(mobility20);

    }

    private void loadResearchAwards() {
        ResearchAward researchAward1 = new ResearchAward();
        researchAward1.setProjectTitle("Abstracted Reading");
        researchAward1.setStaffId("20000001");
        researchAward1.setTypeOfGrant("General Purpose");
        researchAward1.setCoInvestigators("Roberto Castillo");
        researchAward1.setResearchGrantScheme("8000");
        researchAward1.setAwardAmount("18300");
        researchAwardRepository.save(researchAward1);

        ResearchAward researchAward2 = new ResearchAward();
        researchAward2.setProjectTitle("Outstanding Value");
        researchAward2.setStaffId("20000002");
        researchAward2.setTypeOfGrant("Program Development");
        researchAward2.setCoInvestigators("Roberta Carr");
        researchAward2.setResearchGrantScheme("1300");
        researchAward2.setAwardAmount("1300");
        researchAwardRepository.save(researchAward2);

        ResearchAward researchAward3 = new ResearchAward();
        researchAward3.setProjectTitle("Quantum Measure");
        researchAward3.setStaffId("20000003");
        researchAward3.setTypeOfGrant("Operating Support");
        researchAward3.setCoInvestigators("Eloise Cooper");
        researchAward3.setResearchGrantScheme("2800");
        researchAward3.setAwardAmount("3100");
        researchAwardRepository.save(researchAward3);

        ResearchAward researchAward4 = new ResearchAward();
        researchAward4.setProjectTitle("Loop Produce");
        researchAward4.setStaffId("20000004");
        researchAward4.setTypeOfGrant("General Purpose");
        researchAward4.setCoInvestigators("Carroll Chapman");
        researchAward4.setResearchGrantScheme("2100");
        researchAward4.setAwardAmount("3400");
        researchAwardRepository.save(researchAward4);

        ResearchAward researchAward5 = new ResearchAward();
        researchAward5.setProjectTitle("Risk Connection");
        researchAward5.setStaffId("20000005");
        researchAward5.setTypeOfGrant("Operating Support");
        researchAward5.setCoInvestigators("Daniella Parker");
        researchAward5.setResearchGrantScheme("3200");
        researchAward5.setAwardAmount("5100");
        researchAwardRepository.save(researchAward5);

        ResearchAward researchAward6 = new ResearchAward();
        researchAward6.setProjectTitle("Benchmark Stream");
        researchAward6.setStaffId("20000006");
        researchAward6.setTypeOfGrant("Program Development");
        researchAward6.setCoInvestigators("Charles Mckinney");
        researchAward6.setResearchGrantScheme("1900");
        researchAward6.setAwardAmount("3600");
        researchAwardRepository.save(researchAward6);

        ResearchAward researchAward7 = new ResearchAward();
        researchAward7.setProjectTitle("Re-enforced Learning");
        researchAward7.setStaffId("20000007");
        researchAward7.setTypeOfGrant("Project Support");
        researchAward7.setCoInvestigators("Johnnie Stone");
        researchAward7.setResearchGrantScheme("1000");
        researchAward7.setAwardAmount("3100");
        researchAwardRepository.save(researchAward7);

        ResearchAward researchAward8 = new ResearchAward();
        researchAward8.setProjectTitle("Computer Optimization");
        researchAward8.setStaffId("20000008");
        researchAward8.setTypeOfGrant("General Purpose");
        researchAward8.setCoInvestigators("Gwen Brewer");
        researchAward8.setResearchGrantScheme("8200");
        researchAward8.setAwardAmount("8300");
        researchAwardRepository.save(researchAward8);

        ResearchAward researchAward9 = new ResearchAward();
        researchAward9.setProjectTitle("Execution Model");
        researchAward9.setStaffId("20000009");
        researchAward9.setTypeOfGrant("Operating Support");
        researchAward9.setCoInvestigators("Paulette Blair");
        researchAward9.setResearchGrantScheme("9100");
        researchAward9.setAwardAmount("2300");
        researchAwardRepository.save(researchAward9);

        ResearchAward researchAward10 = new ResearchAward();
        researchAward10.setProjectTitle("Portable Vendors");
        researchAward10.setStaffId("20000010");
        researchAward10.setTypeOfGrant("Project Support");
        researchAward10.setCoInvestigators("Alison Goodwin");
        researchAward10.setResearchGrantScheme("1900");
        researchAward10.setAwardAmount("10200");
        researchAwardRepository.save(researchAward10);



    }


    private Authority createAuthority(String roleCode, String roleDescription) {
        Authority authority = new Authority();
        authority.setRoleCode(roleCode);
        authority.setRoleDescription(roleDescription);
        return authority;
    }


}

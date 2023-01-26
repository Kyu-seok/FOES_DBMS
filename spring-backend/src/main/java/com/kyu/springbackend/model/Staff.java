package com.kyu.springbackend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class Staff {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String title;
    private String miriId;
    private String perthId;
    private Date reportDutyDate;
    private String department;
    private String position;
    private String roomNo;
    private String extNo;
    private String status;
    private String email;
    private String appointmentLevel;
    private String photocopyId;
    private String pigeonboxNo;
    private Date resignedDate;
    private String remark;

    public void update(Staff obj) {

        this.firstName = obj.getFirstName();
        this.lastName = obj.getLastName();
        this.title = obj.getTitle();
        this.miriId = obj.getMiriId();
        this.perthId = obj.getPerthId();
        this.reportDutyDate = obj.getReportDutyDate();
        this.department = obj.getDepartment();
        this.position = obj.getPosition();
        this.roomNo = obj.getRoomNo();
        this.extNo = obj.getExtNo();
        this.status = obj.getStatus();
        this.email = obj.getEmail();
        this.appointmentLevel = obj.getAppointmentLevel();
        this.photocopyId = obj.getPhotocopyId();
        this.pigeonboxNo = obj.getPigeonboxNo();
        this.resignedDate = obj.getResignedDate();
        this.remark = obj.getRemark();

    }
}

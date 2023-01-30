package com.kyu.springbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Mobility {
    @Id
    private String id;
    private String staffOrStudent;
    private String inOrOutBound;
    private String name;
    private String attendeeId;
    private String program;
    private String nameOfUniversity;
    private String country;
    private String duration;
    private String fromDate;
    private String toDate;
    private String remark;

    public void update(Mobility obj) {
        this.staffOrStudent = obj.getStaffOrStudent();
        this.inOrOutBound = obj.getInOrOutBound();
        this.name = obj.getName();
        this.attendeeId = obj.getAttendeeId();
        this.program = obj.getProgram();
        this.nameOfUniversity = obj.getNameOfUniversity();
        this.country = obj.getCountry();
        this.duration = obj.getDuration();
        this.fromDate = obj.getFromDate();
        this.toDate = obj.getToDate();
        this.remark = obj.getRemark();
    }

}

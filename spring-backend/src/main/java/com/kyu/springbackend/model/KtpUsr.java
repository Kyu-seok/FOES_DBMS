package com.kyu.springbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class KtpUsr {
    @Id
    private String id;
    private String programName;
    private String communityIndustryName;
    private String category;
    private String date;
    private String location;
    private String leadBy;
    private String faculty;
    private String cmDriven;
    private String partnerName;
    private String noOfStaff;
    private String noOfStudent;
    private String internalFunding;
    private String externalFunding;

    public void update(KtpUsr obj) {
        this.category = obj.getCategory();
        this.date = obj.getDate();
        this.programName = obj.getProgramName();
        this.communityIndustryName = obj.getCommunityIndustryName();
        this.location = obj.getLocation();
        this.leadBy = obj.getLeadBy();
        this.faculty = obj.getFaculty();
        this.cmDriven = obj.getCmDriven();
        this.partnerName = obj.getPartnerName();
        this.noOfStaff = obj.getNoOfStaff();
        this.noOfStudent = obj.getNoOfStudent();
        this.internalFunding = obj.getInternalFunding();
        this.externalFunding = obj.getExternalFunding();
    }
}

package com.kyu.springbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class MouMoa {
    @Id
    private String id;
    private String country;
    private String institution;
    private String signedDate;
    private String dueDate;
    private String areaOfCollaboration;
    private String progress;
    private String typeOfAgreement;
    private String research;
    private String teaching;
    private String exchange;
    private String collaborationAndPartnerships;
    private String mutualExtension;

    public void update(MouMoa obj) {
        this.country = obj.getCountry();
        this.institution = obj.getInstitution();
        this.signedDate = obj.getSignedDate();
        this.dueDate = obj.getDueDate();
        this.areaOfCollaboration = obj.getAreaOfCollaboration();
        this.progress = obj.getProgress();
        this.typeOfAgreement = obj.getTypeOfAgreement();
        this.research = obj.getResearch();
        this.teaching = obj.getTeaching();
        this.exchange = obj.getExchange();
        this.collaborationAndPartnerships = obj.getCollaborationAndPartnerships();
        this.mutualExtension = obj.getMutualExtension();
    }
}

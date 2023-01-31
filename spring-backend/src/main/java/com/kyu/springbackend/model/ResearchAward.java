package com.kyu.springbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class ResearchAward {

    @Id
    private String id;
    private String projectTitle;
    private String staffId;
    private String typeOfGrant;
    private String coInvestigators;
    private String researchGrantScheme;
    private String awardAmount;
    private String evidenceLink;

    public void update(ResearchAward obj) {
        this.staffId = obj.getStaffId();
        this.typeOfGrant = obj.getTypeOfGrant();
        this.projectTitle = obj.getProjectTitle();
        this.coInvestigators = obj.getCoInvestigators();
        this.researchGrantScheme = obj.getResearchGrantScheme();
        this.awardAmount = obj.getAwardAmount();
        this.evidenceLink = obj.getEvidenceLink();
    }
}

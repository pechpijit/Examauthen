package com.droiddev.sdu.teacher.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ModelActivity {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("subjectCode")
    @Expose
    private String subjectCode;
    @SerializedName("subjectName")
    @Expose
    private String subjectName;
    @SerializedName("faculty")
    @Expose
    private String faculty;
    @SerializedName("major")
    @Expose
    private String major;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("start")
    @Expose
    private String start;
    @SerializedName("stop")
    @Expose
    private String stop;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("report")
    @Expose
    private String report;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("committee")
    @Expose
    private ArrayList<ModelProfile> committee = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<ModelProfile> getCommittee() {
        return committee;
    }

    public void setCommittee(ArrayList<ModelProfile> committee) {
        this.committee = committee;
    }

}
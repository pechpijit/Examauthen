package com.droiddev.sdu.admin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelMajor {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("majorName")
    @Expose
    private String majorName;
    @SerializedName("majorDetail")
    @Expose
    private String majorDetail;
    @SerializedName("faculty_id")
    @Expose
    private String faculty_id;
    @SerializedName("faculty")
    @Expose
    private String faculty;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getMajorDetail() {
        return majorDetail;
    }

    public void setMajorDetail(String majorDetail) {
        this.majorDetail = majorDetail;
    }

    public String getFaculty_id() {
        return faculty_id;
    }

    public void setFaculty_id(String faculty_id) {
        this.faculty_id = faculty_id;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}

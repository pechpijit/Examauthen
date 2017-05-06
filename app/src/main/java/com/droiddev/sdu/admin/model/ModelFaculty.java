package com.droiddev.sdu.admin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelFaculty {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("facultyName")
    @Expose
    private String facultyName;
    @SerializedName("facultyDetail")
    @Expose
    private String facultyDetail;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getFacultyDetail() {
        return facultyDetail;
    }

    public void setFacultyDetail(String facultyDetail) {
        this.facultyDetail = facultyDetail;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}

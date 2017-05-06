package com.droiddev.sdu.teacher.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelNews {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("newsName")
    @Expose
    private String newsName;
    @SerializedName("newsDetail")
    @Expose
    private String newsDetail;
    @SerializedName("newsImage")
    @Expose
    private String newsImage;
    @SerializedName("newsYear")
    @Expose
    private Integer newsYear;
    @SerializedName("newsStatus")
    @Expose
    private Integer newsStatus;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getNewsDetail() {
        return newsDetail;
    }

    public void setNewsDetail(String newsDetail) {
        this.newsDetail = newsDetail;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    public Integer getNewsYear() {
        return newsYear;
    }

    public void setNewsYear(Integer newsYear) {
        this.newsYear = newsYear;
    }

    public Integer getNewsStatus() {
        return newsStatus;
    }

    public void setNewsStatus(Integer newsStatus) {
        this.newsStatus = newsStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}

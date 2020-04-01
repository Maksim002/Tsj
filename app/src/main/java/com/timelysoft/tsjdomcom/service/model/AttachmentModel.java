package com.timelysoft.tsjdomcom.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttachmentModel {
    @SerializedName("FileName")
    @Expose
    private String fileName;
    @SerializedName("FilePath")
    @Expose
    private String filePath;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}

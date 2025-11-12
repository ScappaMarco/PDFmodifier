package com.PDFmodifier.PDFmodifier.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filerName;
    private String filePath;
    private LocalDate uploadDate;
    private Long pagesNumber;
    private Long ownerId;

    public Document(Long id, String fileName, String filePath, LocalDate uploadDate, Long pagesNumber, Long ownerId) {
        this.id = id;
        this.filerName = fileName;
        this.filePath = filePath;
        this.uploadDate = uploadDate;
        this.pagesNumber = pagesNumber;
        this.ownerId = ownerId;
    }

    public Document(String fileName, String filePath, LocalDate uploadDate, Long pagesNumber, Long ownerId) {
        this.filerName = fileName;
        this.filePath = filePath;
        this.uploadDate = uploadDate;
        this.pagesNumber = pagesNumber;
        this.ownerId = ownerId;
    }

    public Document(String fileName, String filePath, LocalDate uploadDate, Long pagesNumber) {
        this.filerName = fileName;
        this.filePath = filePath;
        this.uploadDate = uploadDate;
        this.pagesNumber = pagesNumber;
    }

    public Document() {}

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilerName() {
        return this.filerName;
    }

    public void setFilerName(String filerName) {
        this.filerName = filerName;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDate getUploadDate() {
        return this.uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Long getPagesNumber() {
        return this.pagesNumber;
    }

    public void setPagesNumber(Long pagesNumber) {
        this.pagesNumber = pagesNumber;
    }

    public Long getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
package com.PDFmodifier.PDFmodifier.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pdf_operations")
public class PdfOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long documentId;
    private Long userId;
    private String documentOperation;
    private List<Long> pages;
    private LocalDate operationDate;

    public PdfOperation(Long documentId, Long userId, String operation, List<Long> pages, LocalDate operationDate) {
        this.documentId = documentId;
        this.userId = userId;
        this.documentOperation = operation;
        this.pages = pages;
        this.operationDate = operationDate;
    }

    public PdfOperation() {}

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDocumentId() {
        return this.documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDocumentOperation() {
        return this.documentOperation;
    }

    public void setDocumentOperation(String documentOperation) {
        this.documentOperation = documentOperation;
    }

    public List<Long> getPages() {
        return this.pages;
    }

    public void addPage(Long page) {
        this.getPages().add(page);
    }

    public void removePage(Long page) {
        this.getPages().remove(page);
    }

    public void setPages(List<Long> pages) {
        this.pages = pages;
    }

    public LocalDate getOperationDate() {
        return this.operationDate;
    }

    public void setOperationDate(LocalDate operationDate) {
        this.operationDate = operationDate;
    }
}